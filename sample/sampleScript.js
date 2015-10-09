var repo = cmis.login("admin","admin","http://localhost:8080/core/atom/bedroom", "bedroom");

var rootFolder = repo.getRootFolder();
var newFolder = rootFolder.createFolder("jmeter");

var file = cmis.getLocalFile("/Users/totanitakeshi/git/CmisJSConsole/README.md");
var doc1 = newFolder.createDocument(params.get("name"), file);
