/**
 * 
 */
package com.oicl.deployer.language;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

/**
 * @author JAYANGA
 *
 */
public class LanguageValidator {

	/**
	 * 
	 * @param schemaPath Path to the schema(XSD) file.
	 */
	public LanguageValidator(String schemaPath) {
		this.schemaFile = new File(schemaPath);
	}

	/**
	 * 
	 * @param schemaFile XML schema file.
	 */
	public LanguageValidator(File schemaFile) {
		this.schemaFile = schemaFile;
	}

	/**
	 * Validate the given XML file against the given XML schema.
	 * 
	 * @param sourceFile
	 *            Source file to be validated against the schema.
	 * @return Result of the validation.
	 * @throws SAXException
	 * @throws IOException
	 */
	public Result validate(File sourceFile) throws SAXException, IOException {

		if (sourceFile == null) {
			throw new IllegalArgumentException();
		}

		Source xmlFile = new StreamSource(sourceFile);

		SchemaFactory schemaFactory = SchemaFactory
				.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);

		Schema schema = schemaFactory.newSchema(schemaFile);
		Validator validator = schema.newValidator();

		Result result = new StreamResult();

		validator.validate(xmlFile, result);

		return result;
	}

	/**
	 * Validate the XML file in the given path against the XML schema.
	 * 
	 * @param sourcePath
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 */
	public Result validate(String sourcePath) throws SAXException, IOException {

		if (sourcePath.isEmpty()) {
			throw new IllegalArgumentException();
		}

		File sourceFile = new File(sourcePath);

		return this.validate(sourceFile);
	}

	private File schemaFile;	
}
