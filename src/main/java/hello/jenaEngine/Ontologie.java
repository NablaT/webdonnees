package hello.jenaEngine;

import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.graph.Graph;
import org.apache.jena.graph.Node;
import org.apache.jena.graph.Triple;
import org.apache.jena.iri.impl.Main;
import org.apache.jena.ontology.*;
import org.apache.jena.rdf.model.*;
import org.apache.jena.reasoner.Derivation;
import org.apache.jena.reasoner.Reasoner;
import org.apache.jena.reasoner.ValidityReport;
import org.apache.jena.shared.Command;
import org.apache.jena.shared.Lock;
import org.apache.jena.shared.PrefixMapping;
import org.apache.jena.util.iterator.ExtendedIterator;
import org.apache.jena.vocabulary.VCARD;
import org.apache.jena.vocabulary.XSD;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by jonathan on 07/02/16.
 */
public class Ontologie {

    public OntModel load() {
        FileInputStream in;
        OntModel ontologie = new OntModel() {
            @Override
            public ExtendedIterator<Ontology> listOntologies() {
                return null;
            }

            @Override
            public ExtendedIterator<OntProperty> listOntProperties() {
                return null;
            }

            @Override
            public ExtendedIterator<OntProperty> listAllOntProperties() {
                return null;
            }

            @Override
            public ExtendedIterator<ObjectProperty> listObjectProperties() {
                return null;
            }

            @Override
            public ExtendedIterator<DatatypeProperty> listDatatypeProperties() {
                return null;
            }

            @Override
            public ExtendedIterator<FunctionalProperty> listFunctionalProperties() {
                return null;
            }

            @Override
            public ExtendedIterator<TransitiveProperty> listTransitiveProperties() {
                return null;
            }

            @Override
            public ExtendedIterator<SymmetricProperty> listSymmetricProperties() {
                return null;
            }

            @Override
            public ExtendedIterator<InverseFunctionalProperty> listInverseFunctionalProperties() {
                return null;
            }

            @Override
            public ExtendedIterator<Individual> listIndividuals() {
                return null;
            }

            @Override
            public ExtendedIterator<Individual> listIndividuals(Resource cls) {
                return null;
            }

            @Override
            public ExtendedIterator<OntClass> listClasses() {
                return null;
            }

            @Override
            public ExtendedIterator<OntClass> listHierarchyRootClasses() {
                return null;
            }

            @Override
            public ExtendedIterator<EnumeratedClass> listEnumeratedClasses() {
                return null;
            }

            @Override
            public ExtendedIterator<UnionClass> listUnionClasses() {
                return null;
            }

            @Override
            public ExtendedIterator<ComplementClass> listComplementClasses() {
                return null;
            }

            @Override
            public ExtendedIterator<IntersectionClass> listIntersectionClasses() {
                return null;
            }

            @Override
            public ExtendedIterator<OntClass> listNamedClasses() {
                return null;
            }

            @Override
            public ExtendedIterator<Restriction> listRestrictions() {
                return null;
            }

            @Override
            public ExtendedIterator<AnnotationProperty> listAnnotationProperties() {
                return null;
            }

            @Override
            public ExtendedIterator<AllDifferent> listAllDifferent() {
                return null;
            }

            @Override
            public ExtendedIterator<DataRange> listDataRanges() {
                return null;
            }

            @Override
            public Ontology getOntology(String uri) {
                return null;
            }

            @Override
            public Individual getIndividual(String uri) {
                return null;
            }

            @Override
            public OntProperty getOntProperty(String uri) {
                return null;
            }

            @Override
            public ObjectProperty getObjectProperty(String uri) {
                return null;
            }

            @Override
            public TransitiveProperty getTransitiveProperty(String uri) {
                return null;
            }

            @Override
            public SymmetricProperty getSymmetricProperty(String uri) {
                return null;
            }

            @Override
            public InverseFunctionalProperty getInverseFunctionalProperty(String uri) {
                return null;
            }

            @Override
            public DatatypeProperty getDatatypeProperty(String uri) {
                return null;
            }

            @Override
            public AnnotationProperty getAnnotationProperty(String uri) {
                return null;
            }

            @Override
            public OntResource getOntResource(String uri) {
                return null;
            }

            @Override
            public OntResource getOntResource(Resource res) {
                return null;
            }

            @Override
            public OntClass getOntClass(String uri) {
                return null;
            }

            @Override
            public ComplementClass getComplementClass(String uri) {
                return null;
            }

            @Override
            public EnumeratedClass getEnumeratedClass(String uri) {
                return null;
            }

            @Override
            public UnionClass getUnionClass(String uri) {
                return null;
            }

            @Override
            public IntersectionClass getIntersectionClass(String uri) {
                return null;
            }

            @Override
            public Restriction getRestriction(String uri) {
                return null;
            }

            @Override
            public HasValueRestriction getHasValueRestriction(String uri) {
                return null;
            }

            @Override
            public SomeValuesFromRestriction getSomeValuesFromRestriction(String uri) {
                return null;
            }

            @Override
            public AllValuesFromRestriction getAllValuesFromRestriction(String uri) {
                return null;
            }

            @Override
            public CardinalityRestriction getCardinalityRestriction(String uri) {
                return null;
            }

            @Override
            public MinCardinalityRestriction getMinCardinalityRestriction(String uri) {
                return null;
            }

            @Override
            public MaxCardinalityRestriction getMaxCardinalityRestriction(String uri) {
                return null;
            }

            @Override
            public QualifiedRestriction getQualifiedRestriction(String uri) {
                return null;
            }

            @Override
            public CardinalityQRestriction getCardinalityQRestriction(String uri) {
                return null;
            }

            @Override
            public MinCardinalityQRestriction getMinCardinalityQRestriction(String uri) {
                return null;
            }

            @Override
            public MaxCardinalityQRestriction getMaxCardinalityQRestriction(String uri) {
                return null;
            }

            @Override
            public Ontology createOntology(String uri) {
                return null;
            }

            @Override
            public Individual createIndividual(Resource cls) {
                return null;
            }

            @Override
            public Individual createIndividual(String uri, Resource cls) {
                return null;
            }

            @Override
            public OntProperty createOntProperty(String uri) {
                return null;
            }

            @Override
            public ObjectProperty createObjectProperty(String uri) {
                return null;
            }

            @Override
            public ObjectProperty createObjectProperty(String uri, boolean functional) {
                return null;
            }

            @Override
            public TransitiveProperty createTransitiveProperty(String uri) {
                return null;
            }

            @Override
            public TransitiveProperty createTransitiveProperty(String uri, boolean functional) {
                return null;
            }

            @Override
            public SymmetricProperty createSymmetricProperty(String uri) {
                return null;
            }

            @Override
            public SymmetricProperty createSymmetricProperty(String uri, boolean functional) {
                return null;
            }

            @Override
            public InverseFunctionalProperty createInverseFunctionalProperty(String uri) {
                return null;
            }

            @Override
            public InverseFunctionalProperty createInverseFunctionalProperty(String uri, boolean functional) {
                return null;
            }

            @Override
            public DatatypeProperty createDatatypeProperty(String uri) {
                return null;
            }

            @Override
            public DatatypeProperty createDatatypeProperty(String uri, boolean functional) {
                return null;
            }

            @Override
            public AnnotationProperty createAnnotationProperty(String uri) {
                return null;
            }

            @Override
            public OntClass createClass() {
                return null;
            }

            @Override
            public OntClass createClass(String uri) {
                return null;
            }

            @Override
            public ComplementClass createComplementClass(String uri, Resource cls) {
                return null;
            }

            @Override
            public EnumeratedClass createEnumeratedClass(String uri, RDFList members) {
                return null;
            }

            @Override
            public UnionClass createUnionClass(String uri, RDFList members) {
                return null;
            }

            @Override
            public IntersectionClass createIntersectionClass(String uri, RDFList members) {
                return null;
            }

            @Override
            public Restriction createRestriction(Property p) {
                return null;
            }

            @Override
            public Restriction createRestriction(String uri, Property p) {
                return null;
            }

            @Override
            public HasValueRestriction createHasValueRestriction(String uri, Property prop, RDFNode value) {
                return null;
            }

            @Override
            public SomeValuesFromRestriction createSomeValuesFromRestriction(String uri, Property prop, Resource cls) {
                return null;
            }

            @Override
            public AllValuesFromRestriction createAllValuesFromRestriction(String uri, Property prop, Resource cls) {
                return null;
            }

            @Override
            public CardinalityRestriction createCardinalityRestriction(String uri, Property prop, int cardinality) {
                return null;
            }

            @Override
            public MinCardinalityRestriction createMinCardinalityRestriction(String uri, Property prop, int cardinality) {
                return null;
            }

            @Override
            public MaxCardinalityRestriction createMaxCardinalityRestriction(String uri, Property prop, int cardinality) {
                return null;
            }

            @Override
            public MaxCardinalityQRestriction createMaxCardinalityQRestriction(String uri, Property prop, int cardinality, OntClass cls) {
                return null;
            }

            @Override
            public MinCardinalityQRestriction createMinCardinalityQRestriction(String uri, Property prop, int cardinality, OntClass cls) {
                return null;
            }

            @Override
            public CardinalityQRestriction createCardinalityQRestriction(String uri, Property prop, int cardinality, OntClass cls) {
                return null;
            }

            @Override
            public DataRange createDataRange(RDFList literals) {
                return null;
            }

            @Override
            public AllDifferent createAllDifferent() {
                return null;
            }

            @Override
            public AllDifferent createAllDifferent(RDFList differentMembers) {
                return null;
            }

            @Override
            public <T extends OntResource> T createOntResource(Class<T> javaClass, Resource rdfType, String uri) {
                return null;
            }

            @Override
            public OntResource createOntResource(String uri) {
                return null;
            }

            @Override
            public void loadImports() {

            }

            @Override
            public Set<String> listImportedOntologyURIs() {
                return null;
            }

            @Override
            public Set<String> listImportedOntologyURIs(boolean closure) {
                return null;
            }

            @Override
            public boolean hasLoadedImport(String uri) {
                return false;
            }

            @Override
            public void addLoadedImport(String uri) {

            }

            @Override
            public void removeLoadedImport(String uri) {

            }

            @Override
            public Profile getProfile() {
                return null;
            }

            @Override
            public ModelMaker getModelMaker() {
                return null;
            }

            @Override
            public ModelMaker getImportModelMaker() {
                return null;
            }

            @Override
            public List<Graph> getSubGraphs() {
                return null;
            }

            @Override
            public ExtendedIterator<OntModel> listImportedModels() {
                return null;
            }

            @Override
            public ExtendedIterator<OntModel> listSubModels(boolean withImports) {
                return null;
            }

            @Override
            public ExtendedIterator<OntModel> listSubModels() {
                return null;
            }

            @Override
            public int countSubModels() {
                return 0;
            }

            @Override
            public OntModel getImportedModel(String uri) {
                return null;
            }

            @Override
            public Model getBaseModel() {
                return null;
            }

            @Override
            public void addSubModel(Model model) {

            }

            @Override
            public void addSubModel(Model model, boolean rebind) {

            }

            @Override
            public void removeSubModel(Model model) {

            }

            @Override
            public void removeSubModel(Model model, boolean rebind) {

            }

            @Override
            public boolean isInBaseModel(RDFNode node) {
                return false;
            }

            @Override
            public boolean isInBaseModel(Statement stmt) {
                return false;
            }

            @Override
            public boolean strictMode() {
                return false;
            }

            @Override
            public void setStrictMode(boolean strict) {

            }

            @Override
            public void setDynamicImports(boolean dynamic) {

            }

            @Override
            public boolean getDynamicImports() {
                return false;
            }

            @Override
            public OntDocumentManager getDocumentManager() {
                return null;
            }

            @Override
            public OntModelSpec getSpecification() {
                return null;
            }

            @Override
            public Model write(Writer writer) {
                return null;
            }

            @Override
            public Model write(Writer writer, String lang) {
                return null;
            }

            @Override
            public Model write(Writer writer, String lang, String base) {
                return null;
            }

            @Override
            public Model write(OutputStream out) {
                return null;
            }

            @Override
            public Model write(OutputStream out, String lang) {
                return null;
            }

            @Override
            public Model write(OutputStream out, String lang, String base) {
                return null;
            }

            @Override
            public Model writeAll(Writer writer, String lang, String base) {
                return null;
            }

            @Override
            public Model writeAll(OutputStream out, String lang, String base) {
                return null;
            }

            @Override
            public Model writeAll(Writer writer, String lang) {
                return null;
            }

            @Override
            public Model writeAll(OutputStream out, String lang) {
                return null;
            }

            @Override
            public Model getRawModel() {
                return null;
            }

            @Override
            public Reasoner getReasoner() {
                return null;
            }

            @Override
            public void rebind() {

            }

            @Override
            public void prepare() {

            }

            @Override
            public void reset() {

            }

            @Override
            public ValidityReport validate() {
                return null;
            }

            @Override
            public StmtIterator listStatements(Resource subject, Property predicate, RDFNode object, Model posit) {
                return null;
            }

            @Override
            public void setDerivationLogging(boolean logOn) {

            }

            @Override
            public Iterator<Derivation> getDerivation(Statement statement) {
                return null;
            }

            @Override
            public Model getDeductionsModel() {
                return null;
            }

            @Override
            public long size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public ResIterator listSubjects() {
                return null;
            }

            @Override
            public NsIterator listNameSpaces() {
                return null;
            }

            @Override
            public Resource getResource(String uri) {
                return null;
            }

            @Override
            public Property getProperty(String nameSpace, String localName) {
                return null;
            }

            @Override
            public Resource createResource() {
                return null;
            }

            @Override
            public Resource createResource(AnonId id) {
                return null;
            }

            @Override
            public Resource createResource(String uri) {
                return null;
            }

            @Override
            public Property createProperty(String nameSpace, String localName) {
                return null;
            }

            @Override
            public Literal createLiteral(String v, String language) {
                return null;
            }

            @Override
            public Literal createLiteral(String v, boolean wellFormed) {
                return null;
            }

            @Override
            public Literal createTypedLiteral(String lex, RDFDatatype dtype) {
                return null;
            }

            @Override
            public Literal createTypedLiteral(Object value, RDFDatatype dtype) {
                return null;
            }

            @Override
            public Literal createTypedLiteral(Object value) {
                return null;
            }

            @Override
            public Statement createStatement(Resource s, Property p, RDFNode o) {
                return null;
            }

            @Override
            public RDFList createList() {
                return null;
            }

            @Override
            public RDFList createList(Iterator<? extends RDFNode> members) {
                return null;
            }

            @Override
            public RDFList createList(RDFNode[] members) {
                return null;
            }

            @Override
            public Model add(Statement s) {
                return null;
            }

            @Override
            public Model add(Statement[] statements) {
                return null;
            }

            @Override
            public Model remove(Statement[] statements) {
                return null;
            }

            @Override
            public Model add(List<Statement> statements) {
                return null;
            }

            @Override
            public Model remove(List<Statement> statements) {
                return null;
            }

            @Override
            public Model add(StmtIterator iter) {
                return null;
            }

            @Override
            public Model add(Model m) {
                return null;
            }

            @Override
            public Model read(String url) {
                return null;
            }

            @Override
            public Model read(InputStream in, String base) {
                return null;
            }

            @Override
            public Model read(InputStream in, String base, String lang) {
                return null;
            }

            @Override
            public Model read(Reader reader, String base) {
                return null;
            }

            @Override
            public Model read(String url, String lang) {
                return null;
            }

            @Override
            public Model read(Reader reader, String base, String lang) {
                return null;
            }

            @Override
            public Model read(String url, String base, String lang) {
                return null;
            }

            @Override
            public Model remove(Statement s) {
                return null;
            }

            @Override
            public Statement getRequiredProperty(Resource s, Property p) {
                return null;
            }

            @Override
            public Statement getProperty(Resource s, Property p) {
                return null;
            }

            @Override
            public ResIterator listSubjectsWithProperty(Property p) {
                return null;
            }

            @Override
            public ResIterator listResourcesWithProperty(Property p) {
                return null;
            }

            @Override
            public ResIterator listSubjectsWithProperty(Property p, RDFNode o) {
                return null;
            }

            @Override
            public ResIterator listResourcesWithProperty(Property p, RDFNode o) {
                return null;
            }

            @Override
            public NodeIterator listObjects() {
                return null;
            }

            @Override
            public NodeIterator listObjectsOfProperty(Property p) {
                return null;
            }

            @Override
            public NodeIterator listObjectsOfProperty(Resource s, Property p) {
                return null;
            }

            @Override
            public boolean contains(Resource s, Property p) {
                return false;
            }

            @Override
            public boolean containsResource(RDFNode r) {
                return false;
            }

            @Override
            public boolean contains(Resource s, Property p, RDFNode o) {
                return false;
            }

            @Override
            public boolean contains(Statement s) {
                return false;
            }

            @Override
            public boolean containsAny(StmtIterator iter) {
                return false;
            }

            @Override
            public boolean containsAll(StmtIterator iter) {
                return false;
            }

            @Override
            public boolean containsAny(Model model) {
                return false;
            }

            @Override
            public boolean containsAll(Model model) {
                return false;
            }

            @Override
            public boolean isReified(Statement s) {
                return false;
            }

            @Override
            public Resource getAnyReifiedStatement(Statement s) {
                return null;
            }

            @Override
            public void removeAllReifications(Statement s) {

            }

            @Override
            public void removeReification(ReifiedStatement rs) {

            }

            @Override
            public StmtIterator listStatements() {
                return null;
            }

            @Override
            public StmtIterator listStatements(Selector s) {
                return null;
            }

            @Override
            public StmtIterator listStatements(Resource s, Property p, RDFNode o) {
                return null;
            }

            @Override
            public ReifiedStatement createReifiedStatement(Statement s) {
                return null;
            }

            @Override
            public ReifiedStatement createReifiedStatement(String uri, Statement s) {
                return null;
            }

            @Override
            public RSIterator listReifiedStatements() {
                return null;
            }

            @Override
            public RSIterator listReifiedStatements(Statement st) {
                return null;
            }

            @Override
            public Model query(Selector s) {
                return null;
            }

            @Override
            public Model union(Model model) {
                return null;
            }

            @Override
            public Model intersection(Model model) {
                return null;
            }

            @Override
            public Model difference(Model model) {
                return null;
            }

            @Override
            public Model begin() {
                return null;
            }

            @Override
            public Model abort() {
                return null;
            }

            @Override
            public Model commit() {
                return null;
            }

            @Override
            public Object executeInTransaction(Command cmd) {
                return null;
            }

            @Override
            public boolean independent() {
                return false;
            }

            @Override
            public boolean supportsTransactions() {
                return false;
            }

            @Override
            public boolean supportsSetOperations() {
                return false;
            }

            @Override
            public boolean isIsomorphicWith(Model g) {
                return false;
            }

            @Override
            public void close() {

            }

            @Override
            public Lock getLock() {
                return null;
            }

            @Override
            public Model register(ModelChangedListener listener) {
                return null;
            }

            @Override
            public Model unregister(ModelChangedListener listener) {
                return null;
            }

            @Override
            public Model notifyEvent(Object e) {
                return null;
            }

            @Override
            public Model removeAll() {
                return null;
            }

            @Override
            public Model removeAll(Resource s, Property p, RDFNode r) {
                return null;
            }

            @Override
            public boolean isClosed() {
                return false;
            }

            @Override
            public void enterCriticalSection(boolean readLockRequested) {

            }

            @Override
            public void leaveCriticalSection() {

            }

            @Override
            public Resource getResource(String uri, ResourceF f) {
                return null;
            }

            @Override
            public Property getProperty(String uri) {
                return null;
            }

            @Override
            public Bag getBag(String uri) {
                return null;
            }

            @Override
            public Bag getBag(Resource r) {
                return null;
            }

            @Override
            public Alt getAlt(String uri) {
                return null;
            }

            @Override
            public Alt getAlt(Resource r) {
                return null;
            }

            @Override
            public Seq getSeq(String uri) {
                return null;
            }

            @Override
            public Seq getSeq(Resource r) {
                return null;
            }

            @Override
            public Resource createResource(Resource type) {
                return null;
            }

            @Override
            public RDFNode getRDFNode(Node n) {
                return null;
            }

            @Override
            public Resource createResource(String uri, Resource type) {
                return null;
            }

            @Override
            public Resource createResource(ResourceF f) {
                return null;
            }

            @Override
            public Resource createResource(String uri, ResourceF f) {
                return null;
            }

            @Override
            public Property createProperty(String uri) {
                return null;
            }

            @Override
            public Literal createLiteral(String v) {
                return null;
            }

            @Override
            public Literal createTypedLiteral(boolean v) {
                return null;
            }

            @Override
            public Literal createTypedLiteral(int v) {
                return null;
            }

            @Override
            public Literal createTypedLiteral(long v) {
                return null;
            }

            @Override
            public Literal createTypedLiteral(Calendar d) {
                return null;
            }

            @Override
            public Literal createTypedLiteral(char v) {
                return null;
            }

            @Override
            public Literal createTypedLiteral(float v) {
                return null;
            }

            @Override
            public Literal createTypedLiteral(double v) {
                return null;
            }

            @Override
            public Literal createTypedLiteral(String v) {
                return null;
            }

            @Override
            public Literal createTypedLiteral(String lex, String typeURI) {
                return null;
            }

            @Override
            public Literal createTypedLiteral(Object value, String typeURI) {
                return null;
            }

            @Override
            public Statement createLiteralStatement(Resource s, Property p, boolean o) {
                return null;
            }

            @Override
            public Statement createLiteralStatement(Resource s, Property p, float o) {
                return null;
            }

            @Override
            public Statement createLiteralStatement(Resource s, Property p, double o) {
                return null;
            }

            @Override
            public Statement createLiteralStatement(Resource s, Property p, long o) {
                return null;
            }

            @Override
            public Statement createLiteralStatement(Resource s, Property p, int o) {
                return null;
            }

            @Override
            public Statement createLiteralStatement(Resource s, Property p, char o) {
                return null;
            }

            @Override
            public Statement createLiteralStatement(Resource s, Property p, Object o) {
                return null;
            }

            @Override
            public Statement createStatement(Resource s, Property p, String o) {
                return null;
            }

            @Override
            public Statement createStatement(Resource s, Property p, String o, String l) {
                return null;
            }

            @Override
            public Statement createStatement(Resource s, Property p, String o, boolean wellFormed) {
                return null;
            }

            @Override
            public Statement createStatement(Resource s, Property p, String o, String l, boolean wellFormed) {
                return null;
            }

            @Override
            public Bag createBag() {
                return null;
            }

            @Override
            public Bag createBag(String uri) {
                return null;
            }

            @Override
            public Alt createAlt() {
                return null;
            }

            @Override
            public Alt createAlt(String uri) {
                return null;
            }

            @Override
            public Seq createSeq() {
                return null;
            }

            @Override
            public Seq createSeq(String uri) {
                return null;
            }

            @Override
            public Model add(Resource s, Property p, RDFNode o) {
                return null;
            }

            @Override
            public Model addLiteral(Resource s, Property p, boolean o) {
                return null;
            }

            @Override
            public Model addLiteral(Resource s, Property p, long o) {
                return null;
            }

            @Override
            public Model addLiteral(Resource s, Property p, int o) {
                return null;
            }

            @Override
            public Model addLiteral(Resource s, Property p, char o) {
                return null;
            }

            @Override
            public Model addLiteral(Resource s, Property p, float o) {
                return null;
            }

            @Override
            public Model addLiteral(Resource s, Property p, double o) {
                return null;
            }

            @Override
            public Model addLiteral(Resource s, Property p, Object o) {
                return null;
            }

            @Override
            public Model addLiteral(Resource s, Property p, Literal o) {
                return null;
            }

            @Override
            public Model add(Resource s, Property p, String o) {
                return null;
            }

            @Override
            public Model add(Resource s, Property p, String lex, RDFDatatype datatype) {
                return null;
            }

            @Override
            public Model add(Resource s, Property p, String o, boolean wellFormed) {
                return null;
            }

            @Override
            public Model add(Resource s, Property p, String o, String l) {
                return null;
            }

            @Override
            public Model remove(Resource s, Property p, RDFNode o) {
                return null;
            }

            @Override
            public Model remove(StmtIterator iter) {
                return null;
            }

            @Override
            public Model remove(Model m) {
                return null;
            }

            @Override
            public StmtIterator listLiteralStatements(Resource subject, Property predicate, boolean object) {
                return null;
            }

            @Override
            public StmtIterator listLiteralStatements(Resource subject, Property predicate, char object) {
                return null;
            }

            @Override
            public StmtIterator listLiteralStatements(Resource subject, Property predicate, long object) {
                return null;
            }

            @Override
            public StmtIterator listLiteralStatements(Resource subject, Property predicate, float object) {
                return null;
            }

            @Override
            public StmtIterator listLiteralStatements(Resource subject, Property predicate, double object) {
                return null;
            }

            @Override
            public StmtIterator listStatements(Resource subject, Property predicate, String object) {
                return null;
            }

            @Override
            public StmtIterator listStatements(Resource subject, Property predicate, String object, String lang) {
                return null;
            }

            @Override
            public ResIterator listResourcesWithProperty(Property p, boolean o) {
                return null;
            }

            @Override
            public ResIterator listResourcesWithProperty(Property p, long o) {
                return null;
            }

            @Override
            public ResIterator listResourcesWithProperty(Property p, char o) {
                return null;
            }

            @Override
            public ResIterator listResourcesWithProperty(Property p, float o) {
                return null;
            }

            @Override
            public ResIterator listResourcesWithProperty(Property p, double o) {
                return null;
            }

            @Override
            public ResIterator listResourcesWithProperty(Property p, Object o) {
                return null;
            }

            @Override
            public ResIterator listSubjectsWithProperty(Property p, String o) {
                return null;
            }

            @Override
            public ResIterator listSubjectsWithProperty(Property p, String o, String l) {
                return null;
            }

            @Override
            public boolean containsLiteral(Resource s, Property p, boolean o) {
                return false;
            }

            @Override
            public boolean containsLiteral(Resource s, Property p, long o) {
                return false;
            }

            @Override
            public boolean containsLiteral(Resource s, Property p, int o) {
                return false;
            }

            @Override
            public boolean containsLiteral(Resource s, Property p, char o) {
                return false;
            }

            @Override
            public boolean containsLiteral(Resource s, Property p, float o) {
                return false;
            }

            @Override
            public boolean containsLiteral(Resource s, Property p, double o) {
                return false;
            }

            @Override
            public boolean containsLiteral(Resource s, Property p, Object o) {
                return false;
            }

            @Override
            public boolean contains(Resource s, Property p, String o) {
                return false;
            }

            @Override
            public boolean contains(Resource s, Property p, String o, String l) {
                return false;
            }

            @Override
            public Statement asStatement(Triple t) {
                return null;
            }

            @Override
            public Graph getGraph() {
                return null;
            }

            @Override
            public RDFNode asRDFNode(Node n) {
                return null;
            }

            @Override
            public Resource wrapAsResource(Node n) {
                return null;
            }

            @Override
            public PrefixMapping setNsPrefix(String prefix, String uri) {
                return null;
            }

            @Override
            public PrefixMapping removeNsPrefix(String prefix) {
                return null;
            }

            @Override
            public PrefixMapping setNsPrefixes(PrefixMapping other) {
                return null;
            }

            @Override
            public PrefixMapping setNsPrefixes(Map<String, String> map) {
                return null;
            }

            @Override
            public PrefixMapping withDefaultMappings(PrefixMapping map) {
                return null;
            }

            @Override
            public String getNsPrefixURI(String prefix) {
                return null;
            }

            @Override
            public String getNsURIPrefix(String uri) {
                return null;
            }

            @Override
            public Map<String, String> getNsPrefixMap() {
                return null;
            }

            @Override
            public String expandPrefix(String prefixed) {
                return null;
            }

            @Override
            public String shortForm(String uri) {
                return null;
            }

            @Override
            public String qnameFor(String uri) {
                return null;
            }

            @Override
            public PrefixMapping lock() {
                return null;
            }

            @Override
            public boolean samePrefixMappingAs(PrefixMapping other) {
                return false;
            }

            @Override
            public RDFReader getReader() {
                return null;
            }

            @Override
            public RDFReader getReader(String lang) {
                return null;
            }

            @Override
            public String setReaderClassName(String lang, String className) {
                return null;
            }

            @Override
            public void resetRDFReaderF() {

            }

            @Override
            public String removeReader(String lang) throws IllegalArgumentException {
                return null;
            }

            @Override
            public RDFWriter getWriter() {
                return null;
            }

            @Override
            public RDFWriter getWriter(String lang) {
                return null;
            }

            @Override
            public String setWriterClassName(String lang, String className) {
                return null;
            }

            @Override
            public void resetRDFWriterF() {

            }

            @Override
            public String removeWriter(String lang) throws IllegalArgumentException {
                return null;
            }
        };
        try
        {
            in=new FileInputStream("ontologie.owl");
            if(in.read() > 0) {
                System.out.println("Je passe dans le if");
                ontologie.read("ontologie.owl");
            } else {
                System.out.println("Je passe dans le else");
                ontologie = create();
            }
        }
        catch (Exception ex)
        {
            System.out.println("Je passe dans le catch");
            ontologie = create();
        }

        return ontologie;
    }

    public OntModel create() {

        // créer un modèle vide
        OntModel ontologie = ModelFactory.createOntologyModel();
        String namespace = "http://notreOnthologie#";
        ontologie.createOntology (namespace);

        //Création des classes
        OntClass profil = ontologie.createClass (namespace + "Profil");
        OntClass song = ontologie.createClass (namespace + "Song");
        OntClass artist = ontologie.createClass (namespace + "Artist");

        //Création des propriétés d'objet
        ObjectProperty hasCloseSong = ontologie.createObjectProperty (namespace + "hasCloseSong");
        hasCloseSong.setDomain(song);
        hasCloseSong.setRange(song);
        ObjectProperty hasArtist = ontologie.createObjectProperty (namespace + "hasArtist");
        hasArtist.setDomain(song);
        hasArtist.setRange(artist);
        ObjectProperty hasCloseArtist = ontologie.createObjectProperty (namespace + "hasCloseArtist");
        hasCloseArtist.setDomain(song);
        hasCloseArtist.setRange(artist);
        ObjectProperty hasPopularSong = ontologie.createObjectProperty (namespace + "hasPopularSong");
        hasPopularSong.setDomain(artist);
        hasPopularSong.setRange(song);
        ObjectProperty hasListened = ontologie.createObjectProperty (namespace + "haslistened");
        hasListened.setDomain(profil);
        hasListened.setRange(song);
        ObjectProperty hasAlbumSong = ontologie.createObjectProperty (namespace + "hasAlbumSong");
        hasAlbumSong.setDomain(song);
        hasAlbumSong.setRange(song);




        //Création des propriétés de données
        DatatypeProperty hasBeenSeen = ontologie.createDatatypeProperty (namespace + "hasBeenSeen");
        hasBeenSeen.setDomain(song);
        hasBeenSeen.setRange(XSD.xint);
        DatatypeProperty hasYoutubeView = ontologie.createDatatypeProperty(namespace + "hasYoutubeView");
        hasYoutubeView.setDomain(song);
        hasYoutubeView.setRange(XSD.xlong);

        Individual i = ontologie.createIndividual("http://notreOnthologie#Yesterday", song);
        Individual i2 = ontologie.createIndividual("http://notreOnthologie#Hello", song);
        i.addProperty(hasCloseSong, i2);

        return ontologie;
    }
}
