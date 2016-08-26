package FHIR.FHIR_test;

import java.util.List;

import ca.uhn.fhir.context.FhirContext;
import ca.uhn.fhir.model.dstu2.resource.Bundle;
import ca.uhn.fhir.model.dstu2.resource.Patient;
import ca.uhn.fhir.model.dstu2.valueset.AdministrativeGenderEnum;
import ca.uhn.fhir.rest.client.IGenericClient;


public class Hellofhir {

	public static void main(String[]args){
		// We're connecting to a DSTU1 compliant server in this example
		FhirContext ctx = FhirContext.forDstu2();
		String serverBase = "http://fhirtest.uhn.ca/baseDstu2";
		 
		IGenericClient client = ctx.newRestfulGenericClient(serverBase);
		 
		searchForPatients(client);
		searchForSmith(client);
	}
		
	private static void searchForPatients(IGenericClient client) {
		// Perform a search
		Bundle results = client
		      .search()
		      .forResource(Patient.class)
		      .where(Patient.FAMILY.matches().value("duck"))
		      .returnBundle(ca.uhn.fhir.model.dstu2.resource.Bundle.class)
		      .execute();
		 
		System.out.println("Found " + results.getEntry().size() + " patients named 'duck'");
		}
	
	private static void searchForSmith(IGenericClient client) {
		Bundle response = client.search()
			      .forResource(Patient.class)
			      .where(Patient.FAMILY.matches().values("Smith", "Smyth"))
			      .returnBundle(Bundle.class)
			      .execute();
		
		System.out.println("Found " + response.getEntry().toString() + " patients named 'duck'");
	}
}
	
