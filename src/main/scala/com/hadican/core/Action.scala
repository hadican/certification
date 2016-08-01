package com.hadican.core

import java.io.{File, FileInputStream, FileOutputStream}
import java.net.{Proxy, URL}
import java.security.KeyStore
import javax.net.ssl.HttpsURLConnection

import sun.security.x509.X509CertImpl

trait Action {

  protected def importCertificate(url: URL, proxy: Proxy, keystoreFile: File, password: Array[Char]) = {
    // try to retrieve SSL certificate
    println("Trying to retrieve SSL certificate.")
    val connection = url.openConnection(proxy).asInstanceOf[HttpsURLConnection]
    connection.connect()

    connection.getServerCertificates.headOption match {
      case Some(certificate) =>
        println("SSL certificate is retrieved.")

        // check validity
        certificate.asInstanceOf[X509CertImpl].checkValidity()
        println("SSL certificate is validated.")

        // open keystore file
        println("Trying to import SSL certificate.")
        val keystoreInputStream = new FileInputStream(keystoreFile)
        val keystore = KeyStore.getInstance(KeyStore.getDefaultType)
        keystore.load(keystoreInputStream, password)

        // add certificate to keystore
        val alias = url.getHost
        keystore.setCertificateEntry(alias, certificate)

        // save keystore file
        val keystoreOutputStream = new FileOutputStream(keystoreFile)
        keystore.store(keystoreOutputStream, password)
        keystoreOutputStream.close()
        println(s"SSL certificate is successfully imported.")

      case None => println("No SSL certificate is found!")
    }

  }

}
