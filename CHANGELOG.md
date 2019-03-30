# Change Log
All notable changes to this project will be documented in this file.
This project does its best to adhere to [Semantic Versioning](http://semver.org/).


--------
### [0.1.2](N/A) - 2019-03-30
#### Changed
* Removed Lombok dependency
* Update to Java 9 (define required modules in `.classpath`)


--------
### [0.1.1](https://github.com/TeamworkGuy2/JTwg2Templating/commit/2fd4954caf3d7000c41f1567dc2e974d4b3c9747) - 2016-08-21
#### Changed
* Updated JSimpleTypes dependency to latest 0.5.0 version
* Moved tests to new test directory
* Switched from versions.md format to CHANGELOG.md, see http://keepachangelog.com/


--------
### [0.1.0](https://github.com/TeamworkGuy2/JTwg2Templating/commit/618810443726cd1239ed2524df26fc86abed4a1d) - 2016-02-08
#### Added
* Initial versioning of existing code, includes utilities for easily generating Java classes, interfaces, and DTOs using the [StringTemplate](http://www.stringtemplate.org/) library
* Fixed TemplateRenderBuilder not closing internal ClassLocation based output stream after rendering template
