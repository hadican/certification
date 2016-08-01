package com.hadican

import java.net._

import com.hadican.config.Config
import com.hadican.core.{Action, Parser}

object Application extends Parser with Action {

  def main(args: Array[String]) = {
    parser.parse(args, Config()) match {
      case Some(config) =>
        val url = new URL(config.url)
        val keystore = config.keystore
        val password = config.password.toCharArray
        val proxy = config.proxy
        importCertificate(url, proxy, keystore, password)
      case None => // bad arguments, error message will have been displayed.
    }
  }

}