# certification
A tool to import certificate of given URL to keystore, programmatically

## Usage
```
Usage: java -jar certification.jar [options]

  -u, --url <value>       URL to import SSL certificate.
  -k, --keystore <value>  Optional keystore path to import SSL certificate. Default value is: $JAVA_HOME/jre/lib/security/cacerts
  -p, --password <value>  Optional password of keystore. Default value is: changeit
  -x, --proxy host:port   Optional proxy address to use. (e.g. proxy.example.com:3128)
  --help                  Prints this usage text.
```

##Examples
- java -jar certification.jar --help
- java -jar certification.jar -u "https://api.example.com"
- java -jar certification.jar -u "https://api.example.com" -k "/path/of/java/keystore" -p "p@assw0rd"
- java -jar certification.jar -u "https://api.example.com" -x "proxy.example.com:3128"

## Build
Use `sbt assembly` to create certification JAR file.
