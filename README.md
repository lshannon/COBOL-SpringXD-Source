# COBOL-SpringXD-Source
A source for reading in COBOL mainframe files

This project requires the installation of some dependencies not available in Maven Central.  Download JRecord from SourceFordge here: [http://jrecord.sourceforge.net/JRecord02.html](http://jrecord.sourceforge.net/JRecord02.html)

```
$ mvn install:install-file -Dfile=<PATH_TO_JRECORD>/lib/cb2xml.jar -DgroupId=net.sf -DartifactId=cb2xml -Dversion=0.80.0 -Dpackaging=jar
$ mvn install:install-file -Dfile=<PATH_TO_JRECORD>/lib/JRecord.jar -DgroupId=net.sf -DartifactId=jrecord -Dversion=0.80.0 -Dpackaging=jar
```

