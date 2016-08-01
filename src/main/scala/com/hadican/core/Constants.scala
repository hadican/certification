package com.hadican.core

import java.io.File

object Constants {

  val keystorePath = System.getProperty("java.home") +
    File.separatorChar + "lib" +
    File.separatorChar + "security" +
    File.separatorChar + "cacerts"

  val keystorePassword = "changeit"
}
