package com.h1.centralvendas.dto.consultas;

public class CRUDServiceProvider {
    private String serviceName = "CRUDServiceProvider.loadRecords";

    public String getServiceName() {
        return serviceName;
    }

    private RequestBody requestBody;

    public void setRequestBody(RequestBody requestBody) {
        this.requestBody = requestBody;
    }

    private static class Criteria{
        private Expression expression = new Expression();

        public Expression getExpression() {
            return expression;
        }

        public void setExpression(String value) {
            this.expression.$ = value;
        }
    }

    public static class DataSet{
        private String rootEntity;        
        private String includePresentationFields = "N";
        private String offsetPage = "0";
        private Criteria criteria = new Criteria();
        public Entity entity = new Entity();

        public DataSet(String SrootEntity, String Scriteria, String Sfieldset){
            this.criteria.setExpression(Scriteria);
            this.rootEntity = SrootEntity;
            Fieldset fieldset = new Fieldset();
            fieldset.setList(Sfieldset);
            this.entity.setFieldset(fieldset);
        }

        public String getRootEntity() {
            return rootEntity;
        }
        public void setRootEntity(String rootEntity) {
            this.rootEntity = rootEntity;
        }
        public void setIncludePresentationFields(String includePresentationFields) {
            this.includePresentationFields = includePresentationFields;
        }
        public void setOffsetPage(String offsetPage) {
            this.offsetPage = offsetPage;
        }
        public Criteria getCriteria() {
            return criteria;
        }
        public void setCriteria(Criteria criteria) {
            this.criteria = criteria;
        }
        public Entity getEntity() {
            return entity;
        }
        public void setEntity(Entity entity) {
            this.entity = entity;
        }
    }

    private static class Entity{
        private Fieldset fieldset;

        public Fieldset getFieldset() {
            return fieldset;
        }

        public void setFieldset(Fieldset fieldset) {
            this.fieldset = fieldset;
        }
    }

    private static class Expression{
        public String $;
    }

    private static class Fieldset{
        private String list;

        public String getList() {
            return list;
        }

        public void setList(String list) {
            this.list = list;
        }
    }

    public static class RequestBody{
        private DataSet dataSet;

        public DataSet getDataSet() {
            return dataSet;
        }

        public RequestBody(DataSet dataSet) {
            this.dataSet = dataSet;
        }

    }

    public RequestBody getRequestBody() {
        return requestBody;
    }

}
