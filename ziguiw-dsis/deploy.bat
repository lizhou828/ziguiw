@echo off
echo clean ws
rmdir /S /Q ws
echo copy source
mkdir ws
cp -r app ws/
cp -r lib ws/
cp -r public ws/
cp -r conf ws/
rm -f ws/conf/application.conf
echo delpy...
pscp -r -pw %ZIGUIW_DEV% ws/* root@10.0.1.22:/usr/local/ziguiw-dsis
rmdir /S /Q lib