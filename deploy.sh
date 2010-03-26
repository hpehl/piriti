#!/bin/sh

VERSION=0.3.1
LOCAL_REPO=/tmp/$(date +%N)
mkdir $LOCAL_REPO
echo [INFO] ------------------------------------------------------------------------
echo [INFO] SVN Checkout to $LOCAL_REPO
echo [INFO] ------------------------------------------------------------------------
svn checkout https://piriti.googlecode.com/svn/repository/name/pehl/piriti $LOCAL_REPO --username harald.pehl

echo
echo [INFO] ------------------------------------------------------------------------
echo [INFO] Copy current version
echo [INFO] ------------------------------------------------------------------------
# Super-POM
cp pom.xml                                      $LOCAL_REPO/piriti-parent/$VERSION/piriti-parent-$VERSION.pom

# Core
cp core/pom.xml                                 $LOCAL_REPO/piriti-core/$VERSION/piriti-core-$VERSION.pom
cp core/target/piriti-core-$VERSION.jar         $LOCAL_REPO/piriti-core/$VERSION/
cp core/target/piriti-core-$VERSION-tests.jar   $LOCAL_REPO/piriti-core/$VERSION/

# GXT
cp gxt/pom.xml                                  $LOCAL_REPO/piriti-gxt/$VERSION/piriti-gxt-$VERSION.pom
cp gxt/target/piriti-gxt-$VERSION.jar           $LOCAL_REPO/piriti-gxt/$VERSION/

# Restlet
cp restlet/pom.xml                              $LOCAL_REPO/piriti-restlet/$VERSION/piriti-restlet-$VERSION.pom
cp restlet/target/piriti-restlet-$VERSION.jar   $LOCAL_REPO/piriti-restlet/$VERSION/

echo
echo [INFO] ------------------------------------------------------------------------
echo [INFO] Commit current version
echo [INFO] ------------------------------------------------------------------------
cd $LOCAL_REPO
svn commit -m "New deployment from deploy.sh"
cd -
rm -rf $LOCAL_REPO

echo [INFO] ------------------------------------------------------------------------
echo [INFO] DEPLOYMENT SUCCESSFUL
echo [INFO] ------------------------------------------------------------------------

