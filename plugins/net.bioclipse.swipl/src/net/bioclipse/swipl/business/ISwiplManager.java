/*******************************************************************************
 * Copyright (c) 2009  The Bioclipse team <olas@bioclipse.net>
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contact: http://www.bioclipse.net/
 ******************************************************************************/
package net.bioclipse.swipl.business;

import java.util.List;

import org.eclipse.core.resources.IFile;

import net.bioclipse.core.PublishedClass;
import net.bioclipse.core.PublishedMethod;
import net.bioclipse.core.Recorded;
import net.bioclipse.core.TestClasses;
import net.bioclipse.managers.business.IBioclipseManager;

@PublishedClass(
    value="Gives access to the SWIPL system, which is a PROLOG based reasoner for Semantic data.	"
)
public interface ISwiplManager extends IBioclipseManager {

	@Recorded
	@PublishedMethod(
			methodSummary="Initialized the prolog engine (loads swipl modules) etc."
	)
	public String init();
	

	@Recorded
	@PublishedMethod(
			params="String filepath",
			methodSummary="Loads a file with Prolog code"
	)
	public String loadPrologFile(String filepath);
    
    @Recorded
    @PublishedMethod(
            params="String[] prologFunction_resultLimit_prologArguments",
            methodSummary="\nTakes an array of strings; 1: The prolog function, 2: Max no. of results, and the rest being arguments passed to the specified prolog function.\n\nExample:\n swipl.queryProlog( [\"prologFunction\",\"10\",\"anAtom\",\"AVariable\"] );\n\nThat will execute a prolog query, like so: \n:- prologFunction(anAtom, AVariable)\n\n...and return 10 results, as unified with the AVariable."
    )   
    public List<List<String>> queryProlog( String[] args );

    @Recorded
    @PublishedMethod(
            params="String prologCode",
            methodSummary="Loads prolog code, as stored in the prologCode variable, into the prolog engine."
    )   
    public String loadPrologCode( String prologCode );
    
    @Recorded
    @PublishedMethod(
            params="String subject, String predicate, String object",
            methodSummary="Executes a prolog query, like so: \":- rdf([predicate], [subject], [object]).\" and prints out all solutions. If subject or object starts with a capital, they will be treated as variables instead as of atoms. rdf/3 is a method in the rdf_db module."
    )   
    public String queryRDF(String subject, String predicate, String object);       
    
    @Recorded
    @PublishedMethod(
            params="String rdfFile",
            methodSummary="Invokes loading of an rdfFile into prolog and stores it in " +
            		      "a variable (RDF_data). The loading makes use of SWI-Prolog's " +
            		      "semweb package"
    )   
    public boolean loadRDFToProlog(String rdfFile);    

    @Recorded
	@PublishedMethod(
			methodSummary="Prints the value of java.library.path"
	)
	public String printLibPath();
	
	@Recorded
	@PublishedMethod(
			methodSummary="Prints the arguments that the current Prolog engine was called with"
	)
	public String getActualArgs();

}
