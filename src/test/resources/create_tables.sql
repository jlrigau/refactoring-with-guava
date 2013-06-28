DROP TABLE IF EXISTS TEAM_MEMBERS_BY_USER;

CREATE TABLE TEAM_MEMBERS_BY_USER  (
    USER_ID decimal(19,0) NOT NULL,
    MEMBER_ID decimal(19,0) NOT NULL,
    CONSTRAINT PK_TEAM_MEMBERS_BY_USER PRIMARY KEY (USER_ID, MEMBER_ID)
);