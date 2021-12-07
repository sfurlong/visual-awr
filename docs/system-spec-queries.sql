SELECT CPU_COUNT,CPU_CORE_COUNT,CPU_SOCKET_COUNT
				 FROM DBA_CPU_USAGE_STATISTICS 
				where dbid = 1836093013
				  and TIMESTAMP = (select max(TIMESTAMP) from DBA_CPU_USAGE_STATISTICS where dbid = 1836093013 )
				  AND ROWNUM = 1;
                                  
SELECT distinct platform_name FROM sys.GV_$DATABASE 
				where dbid = 1836093013
				and rownum = 1;    

SELECT distinct   host_name 
			     FROM dba_hist_database_instance i,dba_hist_snapshot s
				WHERE i.dbid = s.dbid
				  and i.dbid = 1836093013
                  and s.startup_time = i.startup_time
				  AND s.snap_id BETWEEN 385 AND 455
			    order by 1;                   
                            
SELECT sys_context('USERENV', 'MODULE') module FROM DUAL;

SELECT 
      CASE WHEN stat_name = 'PHYSICAL_MEMORY_BYTES' THEN 'PHYSICAL_MEMORY_GB' ELSE stat_name END stat_name,
      CASE WHEN stat_name IN ('PHYSICAL_MEMORY_BYTES') THEN round(VALUE/1024/1024/1024,2) ELSE VALUE END stat_value
  FROM dba_hist_osstat 
 WHERE dbid = 1836093013 
   AND snap_id = (SELECT MAX(snap_id) FROM dba_hist_osstat WHERE dbid = 1836093013 AND instance_number = 1)
		   AND instance_number = 1
   AND (stat_name LIKE 'NUM_CPU%'
   OR stat_name IN ('PHYSICAL_MEMORY_BYTES'));
