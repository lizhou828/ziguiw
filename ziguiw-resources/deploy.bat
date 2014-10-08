rmdir /S /Q ws
mkdir ws
cp -r app ws/
cp -r lib ws/
cp -r public ws/
cp -r conf ws/
rm -f ws/conf/application.conf
pscp -r -pw %ZIGUIW_DEV% ws/* root@10.0.1.22:/usr/local/ziguiw-resources
rmdir /S /Q ws