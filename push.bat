git pull
git add -A
git commit -m "update example %date:~0,4%-%date:~5,2%-%date:~8,2% %time:~0,8%"
git config --global push.default matching
git config --global user.name "lenicliu"
git config --global user.emai "lenicliu@163.com"
git push
