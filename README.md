# JsonResponseParser
parsing jsonObjects using gson


## installing:

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        implementation 'com.github.ali-mohebi:JsonResponseParser:1.0.1'
	}
  
  
### important: 

your models should be in gson standard model


#### example model:

```
public class TestModel{

    private int id;
    private String body;
    private String title;
    private String file;
    @SerializedName("file_size")
    private float fileSize;
    @SerializedName("file_name") //TODO: check if server response has the same name
    private String fileName;
    
}

```

where JsonResponse is like:

```
{
    "data": [
        {
            "id": 30,
            "title": "article title",
            "body": "article body",
	    "file": "testUrl",
	    "file_size": 500,
	    "file_name": "test name"
	 }, ...
     ]
}
```


### configuration:

if the server was sending data by any other name than "data", you can set that name in ResponseParser like:

```
mResponseParser.setResponseDataName(responseName);
```

otherwise you don't need to do anything

	
### how to use:

you can use these codes

```
ResponseParser mResponseParser = new ResponseParser();
mResponseParser.addAndNotifyAdapter(mDataSet, response, mAdapter, mPageHandler);
```

in request's listener like bellow:

```
JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        mResponseParser.addAndNotifyAdapter(mDataSet, response, mAdapter, mPageHandler);
			// your additional code here
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> {
			error.printStackTrace();
        });
```

where mAdapter is your RecyclerViewAdapter or ArrayAdapter
and mPageHandler is a listener which you can use it like

```
String mNextPage = "";
mResponseParser.addAndNotifyAdapter(mDataSet, response, mAdapter, next -> mNextPage = next);
```


#### you can also use:

```
mResponseParser.addAndNotifyAdapter(mDataSet, response, mAdapter, null);
```

or

```
mResponseParser.add(mDataSet, response, mPageHandler);
```

or

```
mResponseParser.add(mDataSet, response, null);
```
