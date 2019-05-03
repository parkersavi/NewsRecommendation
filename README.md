# This News Matching Engine matches news headlines to internal employees 

### How to run:
Two ways to interact with the application
1) The application is deployed on AWS
2) You can run the application locally

#### Checking out application deployed on AWS
##### Usage is: 
##### 1) No Publication Date: 
Uses default date of 2019-04-30. Can be changed to use current date
    
    http://newsheadlines-env.p2ucunbpbt.us-east-1.elasticbeanstalk.com/news

#### 2) With Publication Date: 
##### Valid dates are between 2019-03-01 and 2019-04-30    
    http://newsheadlines-env.p2ucunbpbt.us-east-1.elasticbeanstalk.com/news?publicationDate=2019-04-12
**The service will return every news headline published on this day to its list of matched employees. I have chosen
to return a list of employee ids**

**Note:** if there is a no match, then employeeIds array will be empty.

#### Additionally: 

You can choose to get employee information using following api call. 

Valid values are beetween 0 and 10000


    http://newsheadlines-env.p2ucunbpbt.us-east-1.elasticbeanstalk.com/employee?id=3

## Running the application locally
1) Go to **NewsRecommendation/deploy** directory. Run the application using following command
     
     
        java -jar com.ibm-1.0-SNAPSHOT.jar
     
2) If you are running on localhost you can query the data using following 
     
     
        localhost:5000/news?publicationDate=2019-04-12
     
3) You can get employee information using employee Id
  
  
        localhost:5000/employee?id=10
    

#### Following extra columns were added to the employees and news healines tables 
**employees**
 1) id - can used anywhere to reference an employee
 2) interests - employees interests for eg: blockchain, ai etc.

**news_headlines**
 1) id - can used anywhere to reference a news article
 2) tag - news headlines tags telling us what the article is about eg: blockchain
 3) location - news headline location eg: USA, Germany etc
 

Text files **employees.txt** and **news_headlines.txt** can be found under resources directory in the project

Under **com.ibm.data** you can find **EmployeesGenerator** and **NewsHeadlinesGenerator** used to generate the above files


## Here's how alorithm works:
1) We load **10000 employees**  and **3000 news headlines** in memory by reading text files
2) We filter out all the news headlines by publication date
3) We go through each and every filtered news headlines and check if an employee is a match based on 
     1) *employee's interests* and *article tags* 
        eg: employee interests is blockchain and if the article tag is blockchain as well, we will match 
        that article to that employee
        
     AND
     
     2) *employees location*  and *article location*
        eg: If employee's location is USA and article location is USA as well, we will match 
        that article to that employee
4) We return a list of news headlines with a list of matched employees for every article


The algorithm used doesn't grow too much in memory even if more strategies for matching are added. 
If memory isn't an issue then a faster approach would be to use a hashmap instead of lists.
 
# Assumptions
1) Every employees as an interest (max upto 2) and a location (country) eg: ai,blockchain AND USA
2) News Headlines have tags and location eg: blockchain and USA
3) Data has to be loaded into memory
4) New Headlines have valid dates between 2019-03-01 to 2019-04-30


#### There are few other ways we can match news headlines to employees
1) we can keep track of news headline clicks (what interests the employee) and recommend headlines based on his clicks
2) we can also send articles based on employee's background. For example, if the employee is a engineer vs in business development

 
