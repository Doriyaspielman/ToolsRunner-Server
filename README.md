# ToolsRunner-Server

The purpose of the server-side script is to extract only the list of linkedin
users from the output of the python script. 
I used The harvester python script to import the data with this command "python theHarvester.py -d tufin.com -b linkedin".
I extract only those entities to an object and return it to the client.
