## Code style check

Checkstyle is a tool used to help ensure that Java code adheres to a coding standard. It automates
the process of checking Java code, which is maintaining code quality and readability. Project uses
Checkstyle to enforce coding standards.

### Prerequisites

Before setting up Checkstyle, the following software need to be installed, each serving a specific
purpose:

- **IntelliJ IDEA:** for integrating and visualizing coding standard violations directly in the code
  editor
- **Maven:** maven enables the execution of Checkstyle checks either as a standalone goal or as part
  of the build process, ensuring continuous code quality monitoring

### Style Configurations

The project uses - **Google Java Style**.

The Google Java Style Guide to familiarize yourself with the rules can be found at the following
link: https://google.github.io/styleguide/javaguide.html.

### Dependencies and maven plugin

- **Maven plugin:**
    - **Description:** Checkstyle is a tool for checking Java source code for compliance with a
      coding standard or set of rules. It helps maintain code consistency and quality
    - **Artifact ID:** maven-checkstyle-plugin
    - **Group ID:** org.apache.maven.plugins
    - **Version:** 3.3.1
    - **Configuration:**
        - **Execution phase:** validate
        - **Goal:** check
        - **Config location:** src/main/resources/checkstyle/java-google-style.xml
- **Dependencies:**
    - **Artifact ID:** checkstyle
    - **Group ID:** com.puppycrawl.tools
    - **Version:** 10.12.7
    - **Reference guide:** https://checkstyle.sourceforge.io/index.html

### Adding Checkstyle

- **Pull сonfiguration from devrate-service repository (this step is done only once for the initial
  setup of the Checkstyle):**
  Ensure that you have pulled the latest versions of all configuration files (pom.xml), including
  Checkstyle, from the devrate-service repository. This will ensure consistency in Checkstyle
  settings across the entire team

- **Review Checkstyle Configuration:**
  Familiarize yourself with the Checkstyle configuration file (file java-google-style.xml, see
  config
  location from the previous point) to understand the standards and coding rules being applied. This
  will help you better comprehend which aspects of your code will be checked

### Running Checkstyle

Run Checkstyle using the following command in the terminal:

- mvn checkstyle:check (or mvn validate)

In case of any style check errors, the program build will fail with the message 'Failed during
checkstyle execution', than it generates a report listing all violations of the predefined coding
standards in code.

### Checkstyle-report

- Name file report: checkstyle-report.xml
- Location: \target\checkstyle-reports\checkstyle-report.xml

Here's how to interpret the report:

#### Report Structure

- File Name: Indicates the file where the issue was found

- Error: Each error includes the line number, column number, severity, message, and the rule
  violated

- Severity Levels:
    - Error: A serious violation of the coding standard that must be addressed
    - Warning: Indicates a less critical issue that should be fixed to improve code quality
    - Info: Provides information that may be useful but does not necessarily require action

#### Example:

In the example report, we have an error in the file OrderService.java:

- File: OrderService.java
- Line: 29
- Column: 29
- Severity: error
- Message: "Member name 'OrderMapper' must match pattern '^[a-z][a-z0-9][a-zA-Z0-9]*$'."
- Rule Violated: MemberNameCheck

This indicates that the naming convention for a member variable named 'OrderMapper' does not
follow the defined pattern. The member names should start with a lowercase letter followed by a
combination of letters and digits.

#### Addressing issues:

To resolve issues reported by Checkstyle:

- Locate the issue: use the file name, line, and column information to find the exact location of
  the issue in your code
- Understand the rule: refer to the Checkstyle documentation (see Style Configurations in this
  documentation) for more information about the specific rule violated
- Fix the issue: modify your code to comply with the coding standard
- Re-run checkstyle: after making changes, re-run Checkstyle to ensure that all issues have been
  resolved

### Integrating checkStyle with IntelliJ IDEA (recommendation)

CheckStyle's integration with IntelliJ IDEA is essential to maintaining Google standards for Java
development. To integrate Google CheckStyle into IntelliJ IDEA, you need to perform the following
configuration steps:

1. Import Google Java Style in IntelliJ IDEA:

- Download the Google Style scheme from the Google repository.
  You need to download the following file to your local
  PC: https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml

- Open IntelliJ IDEA > navigate to File > Settings > Editor > Code Style, select 'Java', click on
  the three dots icon, choose 'Import Scheme', and select the downloaded style file (from the
  previous point)

- Set GoogleStyle in the Scheme drop-down list

2. Install Checkstyle-IDEA Plugin:

- Navigate to File > Settings > Plugins
- Search for 'Checkstyle-IDEA' and install the plugin

### Tips for Code Compliance

- Always run Checkstyle before committing your code to ensure it meets our coding standards.

