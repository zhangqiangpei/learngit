-- Create the user 
create user IISTP identified by iistp
  default tablespace TBS_IISTP
  temporary tablespace TEMP
  profile DEFAULT;
-- Grant/Revoke role privileges 
grant connect to IISTP;
grant dba to IISTP;
grant resource to IISTP;
-- Grant/Revoke system privileges 
grant unlimited tablespace to IISTP;
