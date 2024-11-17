#!/bin/sh

# 程序的根目录
basedir=/usr/local/familyhelper-finance

PID=$(cat "$basedir/familyhelper.pid")
kill "$PID"
