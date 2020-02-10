# Usage Event Library: Improving Search

### Problem
The existing search perches on the frontend, comparing a query to an eventType's 'usageLibraryEventType,' 'namespace' and 'name' fields by means of the [indexOf][df1] method. What results is an inelastic search that does not accomodate non-exact matches and only these three fields into account. 

### Solution 1: Keep search on the frontend and use a lot of regex
(I have done this already. In case we choose to stick to this - I would be surprised if we do - then I can push that code right away :rocket:)
I made the search use regex and compare the query to all text fields, as opposed to just the three aforementioned ones. It is much better than what we have, just not as flexible as I imagine Solution 3 would make it. 

### Solution 2: [Elasticlunr][df2]
In my quest to look for alternative client-side solutions, I found this "lightweight full-text search engine in Javascript for browser search and offline search" that is based off of [Lunrjs][df3], its stubborn, inelastic older brother. I have not tried it by doing but an example search bar can be found here: http://elasticlunr.com/example/index.html?#
This does feel more flexible than Solution 1 but I don't know whether I can use a package that 
HubSpot did not use before. HubSpotters have used the older brother Lunr before, though:
![alt text](https://github.com/smellslikekeenspirit/jalgorithms/blob/master/Screen%20Shot%202020-02-10%20at%205.23.50%20PM.png)


### Solution 3: Elasticsearch 
Elasticsearch is a backend solution which is unsurprisingly faster, searches across most data types (a benefit we would not need but it implies less meddling with custom data types), and is scalable for when HubSpot bursts with more EventTypes.
Here is a high-level plan of integrating Elasticsearch: 
![alt text](https://github.com/smellslikekeenspirit/jalgorithms/blob/master/Search%20Sequence%20Diagram%20(1).png)

(We can exclude Graphql at the expense of defining our own search endpoint and parsing the SearchResult list into an EventType list)


### Verdict
As you all may know by now, I am siding with Elasticsearch. I will write a class called ```EventTypesSearch``` that will handle all ElasticSearch work - getting the query from a DataFetchingEnvironment, indexing, building a SearchResponse etc) which would be ~300 LOC including imports. The SearchResult list would be sent over an endpoint (Graphql or otherwise), which would demand <50 LOC. Pardon a newbie for estimates that look off.

###### Links:
   [df1]: <https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/indexOf>
   [df2]: [<http://elasticlunr.com/>]
   [df3]: [<http://lunrjs.com/>]

