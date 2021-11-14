package MichaelHardityaJmartFA;
import java.io.*;
import java.lang.reflect.*;
import java.util.Collections;
import java.util.Vector;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;

@SuppressWarnings("serial")
public class JsonTable<T> extends Vector<T>{
	public final String filepath;
	private static final Gson gson = new Gson();
	public JsonTable (Class <T> clazz, String filepath) throws IOException {
		try {
			@SuppressWarnings("unchecked")
			Class<T[]> array = (Class<T[]>) Array.newInstance(clazz, 0).getClass();
			T[] genArray = JsonTable.readJson(array, filepath);
			if (genArray != null) {
			Collections.addAll(this, genArray);
			}
		} catch (FileNotFoundException e) {
			File file = new File(filepath);
			File parent = file.getParentFile();
			parent.mkdirs();
			file.createNewFile();
		}
		this.filepath = filepath;
	}
	public static <T> T readJson (Class <T> clazz, String filepath) throws FileNotFoundException{
		JsonReader readed = new JsonReader(new FileReader(filepath));
		T result = gson.fromJson(readed, clazz);
		return result;
	}
	public void writeJson () throws IOException{
		writeJson(this, this.filepath);
	}
	public static void writeJson (Object object, String filepath) throws IOException{
		//write file
		FileWriter file = new FileWriter(filepath);
		String converted = gson.toJson(object);
		file.write(converted);
		file.close();
	}
}
