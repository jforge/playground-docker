if( !args.size() ){
	println "Need a file to watch..."
	return
}

while( true ){
	File f = new File(args[0])
	f.eachLine{ line ->
	   println line
	}
	sleep 30*1000
}
