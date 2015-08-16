namespace java com.loc.analyst.service
service baymax{

	string isSick(1:list<string> dims)
	
	list<string> recommandHospital(1:string city)
	
}