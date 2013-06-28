package fr.xebia.xke.service.impl;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Predicate;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import fr.xebia.xke.model.*;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ByParticipants implements Predicate<Product> {

    private User user;

    public ByParticipants(User user) {
        this.user = user;
    }

    @Override
    public boolean apply(Product product) {
        return isVisible(user.getTeams(), getParticipantTeams(product.getParticipants()));
    }

    @VisibleForTesting
    List<Team> getParticipantTeams(Participants participants) {
        Multimap<String, Team> participantTeamsByRole = extractParticipantTeamsByRole(participants);

        List<Team> participantTeams = Lists.newArrayList();

        for (Role role : user.getRoles()) {
            if (Role.SALES.equals(role.getName())) {
                participantTeams.addAll(participantTeamsByRole.get(Role.SALES));
            }

            if (Role.ENGINEER.equals(role.getName())) {
                participantTeams.addAll(participantTeamsByRole.get(Role.ENGINEER));
            }

            if (Role.TRADER.equals(role.getName())) {
                participantTeams.addAll(participantTeamsByRole.get(Role.TRADER));
            }
        }

        return participantTeams;
    }

    @VisibleForTesting
    Multimap<String, Team> extractParticipantTeamsByRole(Participants participants) {
        Multimap<String, Team> participantTeamsByRole = ArrayListMultimap.create();

        if (participants != null) {
            addParticipantTeamIfNotNull(participantTeamsByRole, Role.SALES, participants.getSalesTeam());
            addParticipantTeamIfNotNull(participantTeamsByRole, Role.SALES, participants.getSalesInitiatorTeam());

            addParticipantTeamIfNotNull(participantTeamsByRole, Role.ENGINEER, participants.getPricerTeam());
            addParticipantTeamIfNotNull(participantTeamsByRole, Role.ENGINEER, participants.getStructurerTeam());

            addParticipantTeamIfNotNull(participantTeamsByRole, Role.TRADER, participants.getCdsTraderTeam());
            addParticipantTeamIfNotNull(participantTeamsByRole, Role.TRADER, participants.getxCcyTraderTeam());
            addParticipantTeamIfNotNull(participantTeamsByRole, Role.TRADER, participants.getSwapTraderTeam());
        }

        return participantTeamsByRole;
    }

    private void addParticipantTeamIfNotNull(Multimap<String, Team> participantTeamsByRole, String roleName, Team participantTeam) {
        if (participantTeam != null) {
            participantTeamsByRole.put(roleName, participantTeam);
        }
    }

    @VisibleForTesting
    boolean isVisible(Set<Team> userTeams, final Collection<Team> participantTeams) {
        if (participantTeams.isEmpty()) {
            return false;
        }

        return new Predicate<LinkedList<Team>>() {

            @Override
            public boolean apply(LinkedList<Team> userTeams) {
                if (userTeams.isEmpty()) {
                    return false;
                }

                Team currentUserTeam = userTeams.poll();

                return participantTeams.contains(currentUserTeam)
                        || apply(userTeams)
                        || apply(Lists.newLinkedList(currentUserTeam.getTeamMembers()));

            }

        }.apply(Lists.newLinkedList(userTeams));
    }

}
