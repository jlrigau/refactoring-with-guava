package fr.xebia.xke.repository.query;

public class TaskRepositoryQuery {

    public static final String GET_TASKS_FOR_USER_AND_TEAM_MEMBERS_QUERY = ""
            + "SELECT distinct task.id ID, task.product_id PRODUCT_ID, task.task_code TASKCODE\n"
            + "FROM task task\n"
            + "       inner join role role on role.name = task.assigned_role\n"
            + "       left join user assignedUser on assignedUser.name = task.assigned_user\n"
            + "         left join team_members_by_user assignedUserMembers on assignedUserMembers.member_id = assignedUser.id\n"
            + "       left join team assignedTeam on assignedTeam.name = task.assigned_team\n"
            + "         left join team_member assignedTeamMember on assignedTeamMember.team_id = assignedTeam.id\n"
            + "           left join team_members_by_user assignedTeamMembers on assignedTeamMembers.member_id = assignedTeamMember.member_id\n"
            + "WHERE role.name in (:rolesName) \n"
            + "  AND (assignedUserMembers.user_id = :userId\n"
            + "    OR assignedTeamMembers.user_id = :userId)\n";

    public static final String GET_TASKS_FOR_USER_AND_USER_TEAMS_QUERY = ""
            + "SELECT distinct task.id ID, task.product_id PRODUCT_ID, task.task_code TASKCODE\n"
            + "FROM task task\n"
            + "       inner join role role on role.name = task.assigned_role\n"
            + "         inner join user_role userRole on userRole.roles_id = role.id\n"
            + "           inner join user roleUser on roleUser.id = userRole.user_id\n"
            + "       left join user assignedUser on assignedUser.name = task.assigned_user\n"
            + "       left join team assignedTeam on assignedTeam.name = task.assigned_team\n"
            + "         left join team_member assignedTeamMember on assignedTeamMember.team_id = assignedTeam.id\n"
            + "WHERE roleUser.id = :userId\n"
            + "  AND (assignedUser.id = :userId\n"
            + "    OR assignedTeamMember.member_id = :userId)\n";

}
