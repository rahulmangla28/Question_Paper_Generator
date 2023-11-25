# Question Paper Generator

A Backend based application which enables the user to generate the question papers based on various difficulty level.

## Design Considerations

1. Mark for questions of each difficulty is constant.
2. Question Dataset is stored as Json file.
3. Generated Question Paper will be downloaded in PDF format in users pc downloads' folder.
4. The file will be downloaded with name as test.pdf. If downloads folder already contains file named test.pdf, it will override it.
5. Json dataset file path needs to be provided as input while executing the code.

## Sample Datasets

1. Sample dataset can be found [here](https://github.com/rahulmangla28/Question_Paper_Generator/tree/master/Sample%20Dataset).

## Video Presentation




https://github.com/rahulmangla28/Question_Paper_Generator/assets/93324315/749b653d-9a5f-4b76-950b-b782fed100fa



## Steps to run project in local

1. Make sure system has JDK and eclipse installed.
   
   - Steps to install [JDK-17](https://github.com/rahulmangla28/Question_Paper_Generator/wiki/Installation-Steps#steps-to-install-jdk).
   - Steps to install [Eclipse](https://github.com/rahulmangla28/Question_Paper_Generator/wiki/Installation-Steps#steps-to-install-eclipse).
     
2. Clone the repository.
3. Open the project in Eclipse IDE.
4. Run the project from Eclipse IDE using Run (Tab) -> Run or press Ctrl+F11.
5. Provide the Input values as asked in the console tab in IDE.

## Future Enhancements

1. Project can be extended to select questions based on subject and topic.
2. Marks can be varied within a difficulty level.
3. Project can be extended to support large user base.
