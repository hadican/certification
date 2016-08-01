package com.hadican.core

import java.io.File
import java.net.{InetSocketAddress, Proxy}

import com.hadican.config.Config
import scopt._

trait Parser {

  // custom proxy host & port reader
  implicit val proxyRead: Read[Proxy] = Read.reads(proxyAsString => {
    val proxyAsArray = proxyAsString.split(":")
    val host = proxyAsArray(0)
    val port = proxyAsArray(1).toInt
    new Proxy(Proxy.Type.HTTP, new InetSocketAddress(host, port))
  })

  val parser = new OptionParser[Config]("java -jar certification.jar") {
    head("certification", "v1.0")

    opt[String]('u', "url")
      .action((url, config) => config.copy(url = url))
      .text("URL to import SSL certificate.")
      .required()

    opt[File]('k', "keystore")
      .action((keystore, config) => config.copy(keystore = keystore))
      .text(s"Optional keystore path to import SSL certificate. Default value is: ${Constants.keystorePath}")
      .optional()

    opt[String]('p', "password")
      .action((password, config) => config.copy(password = password))
      .text(s"Optional password of keystore. Default value is: ${Constants.keystorePassword}")
      .optional()

    opt[Proxy]('x', "proxy")
      .action((proxy, config) => config.copy(proxy = proxy))
      .text("Optional proxy address to use. (e.g. proxy.example.com:3128)")
      .valueName("host:port")
      .optional()

    help("help").text("Prints this usage text.")
  }

}
