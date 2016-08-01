package com.hadican.config

import java.io.File
import java.net.Proxy

import com.hadican.core.Constants

case class Config(url: String = null,
                  keystore: File = new File(Constants.keystorePath),
                  password: String = Constants.keystorePassword,
                  proxy: Proxy = Proxy.NO_PROXY)
