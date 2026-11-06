package org.skypro.skyshop.model.search;

public class SearchResult {
    private final String id;
    private final String name;
    private final String contentType;

    public SearchResult(String id, String name, String contentType) {
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public String getIdSearch() {
        return id;
    }

    public String getNameSearch() {
        return name;
    }

    public String getContentTypeSearch() {
        return contentType;
    }

    public static SearchResult fromSearchable(Searchable searchable) {
        if (searchable == null) {
            return null;
        }

        String idStr = searchable.getId().toString();
        String nameStr = searchable.getName();
        String typeStr = searchable.getContentType();

        return new SearchResult(idStr, nameStr, typeStr);
    }
}
