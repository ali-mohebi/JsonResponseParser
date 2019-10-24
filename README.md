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
	        implementation 'com.github.ali-mohebi:JsonResponseParser:1.0.0'
	}
  
  
### important: 
your models should be in gson standard model
	
	
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
