package com.example.ryan.roomrep.Classes.House;


import com.example.ryan.roomrep.Classes.House.Amenities.Amenity;
import com.example.ryan.roomrep.Classes.Profile.Profile;
import com.example.ryan.roomrep.Classes.Tenant.Tenant;
import com.example.ryan.roomrep.Classes.Tenant.Validator;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class House {

    private String address;
    private int rent;
    private int size;
    private int bedNumber;
    private int bathNumber;
    private int applicants;
    private boolean isPosted;

    private String description;
    private String url = "";

    private transient List<Amenity> items = new ArrayList<>();

    private List<Utility> utilities;

    private Map<String, Boolean> amenities;

    private List<Profile> profiles;

    private List<Tenant> tenants;

    private String landlordEmail;




    public House(String address, int rent, int size, int bedNumber, int bathNumber, Map<String, Boolean> amenities, List<Utility> utilities, String landlordEmail){
        this.address = address;
        this.rent = rent;
        this.size = size;
        this.bedNumber = bedNumber;
        this.bathNumber = bathNumber;
        this.amenities = amenities;
        this.utilities = utilities;
        this.applicants = 0;
        this.landlordEmail = landlordEmail;
        this.description = "";
        this.isPosted = false;
    }

    public House(String address, int rent, int size, int bedNumber, int bathNumber, Map<String, Boolean> amenities, List<Utility> utilities, String landlordEmail, String description, String url, boolean isPosted, List<Profile> profiles, List<Tenant> tenants){
        this.address = address;
        this.rent = rent;
        this.size = size;
        this.bedNumber = bedNumber;
        this.bathNumber = bathNumber;
        this.amenities = amenities;
        this.utilities = utilities;
        this.description = description;
        this.applicants = 0;
        this.url = url;
        this.landlordEmail = landlordEmail;
        this.isPosted = isPosted;
        this.profiles = profiles;
        this.tenants = tenants;
    }




    public String getAddress() {
        return address;
    }

    public int getRent() {
        return rent;
    }

    public int getSize() {
        return size;
    }

    public int getBedNumber() {
        return bedNumber;
    }

    public int getBathNumber() {
        return bathNumber;
    }

    public List<Utility> getUtilities() {
        return utilities;
    }


    public Map<String, Boolean> getAmenities() {
        return amenities;
    }

    public int getApplicants() {
        return applicants;
    }

    public void setApplicants(int applicants) {
        this.applicants = applicants;
    }

    public String getDescription() {
        return this.description;
    }

    public void addAmenity(Amenity item){
        items.add(item);
    }

    public String getAmenityDescription(){
        this.description = "This house offers amenities such as: ";
        StringBuilder builder = new StringBuilder();

        if (items.size() == 1){
            this.description = "This house offers one amenity, " + items.get(0).name() + ".";
            return this.description;
        }
        if (items.isEmpty()){
            this.description = "This house does not offer any amenities.";
            return this.description;
        }

        builder.append(description);
        int listSize = items.size();
        for (int i = 0; i < items.size(); i++) {
            if (i != listSize - 1){
                builder.append(items.get(i).name() + ", ");
                continue;
            }
            builder.replace(builder.length() - 2, builder.length(), " and ");
            builder.append(items.get(i).name());

        }
        this.description = builder.toString() + ".";
        return this.description;
    }

    public Map<Integer, String> getValidator() {
        Validator validator = new HouseValidator();
        return validator.validator(this);
    }

    public void setUrl(String url){
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }


    public String getLandlordEmail() {
        return landlordEmail;
    }

    public boolean getPosted(){
        return this.isPosted;
    }

    public void setPosted(boolean posted) {
        this.isPosted = posted;
    }


    public List<Profile> getProfiles() {
        return profiles;
    }

    public List<Tenant> getTenants() {
        return tenants;
    }

    public void addTenant(Tenant tenant) {
        this.tenants.add(tenant);
    }

    public void addProfile(Profile profile) {
        this.profiles.add(profile);
    }

    public void initProfileTenant() {
        this.profiles = new ArrayList<>();
        this.tenants = new ArrayList<>();
    }

}




