-- Create the user 
create user IIS identified by iis
  default tablespace TBS_IIS
  temporary tablespace TEMP
  profile DEFAULT;
-- Grant/Revoke role privileges 
grant connect to IIS;
grant dba to IIS;
grant resource to IIS;
-- Grant/Revoke system privileges 
grant unlimited tablespace to IIS;
