<?php

import com.ttProject.quercus.test.ArgManager;
$args = ArgManager::getInstance();
var_dump($args);
$testClass = $args->getValue("test");
var_dump($testClass);
$testClass->test(); // これは呼ばれた瞬間に標準出力に出力されます
// phpの標準出力に関しては、env.executeが終わった後に標準出力のストリームがflushされた瞬間に出力されます。
echo "hogehgoe";

var_dump("hogehoge");

var_dump($_JAVAARG);