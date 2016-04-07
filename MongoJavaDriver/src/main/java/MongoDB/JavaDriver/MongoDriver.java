package MongoDB.JavaDriver;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static Util.Helpers.printJson;
import static java.util.Arrays.asList;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.gt;
import static com.mongodb.client.model.Filters.gte;

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
	
	public void deleteDocument(){
        MongoCollection<Document> collection = db.getCollection("deleteTest");

        collection.drop();

        // insert 8 documents, with _id set to the value of the loop variable
        for (int i = 0; i < 8; i++) {
            collection.insertOne(new Document().append("_id", i));
        }

        collection.deleteOne(eq("_id", 4));

        for (Document cur : collection.find().into(new ArrayList<Document>())) {
            printJson(cur);
        }
	}


	public static void main(String[] args) {
		MongoDriver md = new MongoDriver();
//		md.insertDocument();
		md.deleteDocument();

	}
}
