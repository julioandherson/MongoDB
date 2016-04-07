package MongoDB.JavaDriver;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static Util.Helpers.printJson;
import static java.util.Arrays.asList;

/**
 * Hello world!
 *
 */
public class MongoDriver {
	MongoClient client;
	MongoDatabase db;
	
	public MongoDriver(){
		client = new MongoClient();
		db = client.getDatabase("MongoJavaDriver");
	}

	public void insertDocument() {

		MongoCollection<Document> coll = db.getCollection("insertTest");

		coll.drop();

		Document smith = new Document("name", "Smith").append("age", 30).append("profession", "programmer");

		Document jones = new Document("name", "Jones").append("age", 25).append("profession", "hacker");

		printJson(smith);

		coll.insertOne(smith);
		coll.insertOne(jones);

		printJson(smith);
		printJson(jones);

		coll.drop();

		coll.insertMany(asList(smith, jones));

		printJson(smith);
		printJson(jones);
	}


	public static void main(String[] args) {
		MongoDriver md = new MongoDriver();
		md.insertDocument();

	}
}
