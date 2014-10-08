@echo off
echo clean ws
rmdir /S /Q ws
mkdir ws
echo copy source
cp -r app ws/
cp -r lib ws/
rm -f ws/lib/*
cp lib/ziguiw-common-1.0.jar ws/lib/
cp -r public ws/
cp -r conf ws/
rm -f ws/conf/application.conf
echo delpy...
pscp -r -pw %ZIGUIW_TEST% ws/* root@202.85.212.169:/usr/local/ziguiw-portal
rmdir /S /Q lib