# Container image which we are going to use
FROM softinstigate/graalvm-maven

# Directory we are going to work on
WORKDIR .

# Some tests to see if versions are good
RUN java --version
RUN mvn --version

# Copy files from project and move them to container to /src directory
COPY . .

# Create runnable jar
RUN mvn clean package -DskipTests

WORKDIR target
