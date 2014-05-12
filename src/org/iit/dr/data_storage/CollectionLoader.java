package org.iit.dr.data_storage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.iit.dr.data_storage.exceptions.DataStorageException;
import org.iit.dr.data_storage.object_collections.ListCollection;
import org.iit.dr.utils.FileUtils;

/**
 * 
 * @author Uladzimir Babkou
 * 
 * @param <T>
 */
public class CollectionLoader<T> {
	public static final String JAXB_CONTEXT_STRING = "org.iit.dr";

	private ListCollection<T> listCollection;

	private List<T> list;

	public CollectionLoader(ListCollection<T> listCollection) {
		this.listCollection = listCollection;
	}

	public List<T> getList() {
		if (list == null) {
			try {
				loadCollection();
			}
			catch (Exception e) {
				throw new DataStorageException("Error occurs on loading data",
						e);
			}
		}
		return list;
	}

	private void loadCollection() throws JAXBException, FileNotFoundException {
		JAXBContext jc = JAXBContext.newInstance(JAXB_CONTEXT_STRING);
		Unmarshaller u = jc.createUnmarshaller();
		File file = FileUtils.getCollectionData(listCollection.getName());
		if (file.exists()) {
			FileInputStream in = new FileInputStream(file);
			listCollection = (ListCollection<T>) u.unmarshal(in);
			list = listCollection.getList();
			if (list == null) {
				System.out.println("WARNING: loaded data is null");
				list = new ArrayList<T>();
			}
		} else {
			throw new FileNotFoundException("File " + file.getAbsolutePath()
					+ " not found!");
		}
	}

	private void saveCollection() throws JAXBException, FileNotFoundException {
		if (list != null) {
			JAXBContext jc = JAXBContext.newInstance(JAXB_CONTEXT_STRING);
			Marshaller m = jc.createMarshaller();
			m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			File file = FileUtils.getCollectionData(listCollection.getName());
			OutputStream outputStream = new FileOutputStream(file);
			listCollection.setList(list);
			m.marshal(listCollection, outputStream);
		}
	}

	public void save() {
		try {
			saveCollection();
		} catch (Exception e) {
			throw new DataStorageException("Error occurs on saving data", e);
		}
	}
}
