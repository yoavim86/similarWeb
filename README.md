# web similarities
Calculate similar sites by count of common tags

## Input
tsv file containing URL TAG in each line in the following structure:

SiteName1 TagName1

SiteName2 TagName2

.

.

SiteNamei TagNamei


## Output
Should be the top 10 similar sites per site – sorted by secondary sort.
The output should be a text file in the following structure:

SiteName1 SimilarSiteName1 Count-Of-Common-Tags

SiteName2 SimilarSiteName2 Count-Of-Common-Tags

For Example:

www.facebook.com www.youtube.com 2

www.facebook.com www.gopro.com 1

www.facebook.com www.google.com 1

www.google.com www.facebook.com 1

www.google.com www.youtube.com 1

www.youtube.com www.facebook.com 2

www.youtube.com www.google.com 1

## How to Run
_Instructions require prior Hadoop set up._

1. Clone the repository and open a terminal in the main folder where the `pom.xml` is located.

2. start-all.sh - Starts all Hadoop daemons, the namenode, datanodes, the jobtracker and tasktrackers

3. If successful, run "bash run_map_reduce.sh"

4. If successful you will get similarWebResults text file in the same directory

## Python_test
There is a script which get tags.text file and another file with name 10megaUrl.csv 
which nedd to be downloded from https://www.domcop.com/top-10-million-domains to the same 
directory (324MB)




