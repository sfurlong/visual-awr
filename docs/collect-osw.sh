#/bin/bash
file_name=""
if [ $# -gt 0 ]
then
    file_name="_$1"
fi
 
# A few find examples
# \( -name "*.*" \) \
# \( -name "*vmstat*.*" -o -name "*iostat*.*" \) \
# \( -name "*vmstat*.*" -o -name "*iostat*.*" \) -mmin -60 \
# \( -name "*vmstat*.*" -o -name "*iostat*.*" -o -name "*netstat*.*" \) -mtime -8 \
#    (ssh -n -q root@$line 'find /opt/oracle.oswatcher/osw/archive  \
 
while read line; do
(ssh -n -q root@$line 'find /home/oracle/oswbb/archive  \
    \( -name "*vmstat*.*" -o -name "*iostat*.*" \) \
    -prune -print0 2>/dev/null | \
    xargs -0 tar --no-recursion -czf - 2>/dev/null ' | cat >  osw${file_name}_${line}.tar.gz
)
done < ./allnodes.dat