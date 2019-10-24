# JsonResponseParser
parsing jsonObjects using gson

### installing:

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
  
  
## important: 
	your models should be in gson standard
	
how to use:

```
ResponseParser mResponseParser = new ResponseParser();
mResponseParser.addAndNotifyAdapter(mDataSet, response, mAdapter, mPageHandler);
```

where mAdapter is your RecyclerViewAdapter or ArrayAdapter

you can also use:

```
mResponseParser.add(mDataSet, response, mPageHandler);
```
