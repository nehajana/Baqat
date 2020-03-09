package shubham.com.baqat.RestaurantsCateringScreen.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CateringDataList {


    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("user_name")
    @Expose
    private String userName;
    @SerializedName("mobile_number")
    @Expose
    private String mobileNumber;
    @SerializedName("profile_picture")
    @Expose
    private String profilePicture;
    @SerializedName("media_path")
    @Expose
    private String mediaPath;
    @SerializedName("media_thumb_path")
    @Expose
    private String mediaThumbPath;
    @SerializedName("listing_id")
    @Expose
    private String listingId;
    @SerializedName("listing_title")
    @Expose
    private String listingTitle;
    @SerializedName("listing_content")
    @Expose
    private String listingContent;
    @SerializedName("listing_expiration_date")
    @Expose
    private String listingExpirationDate;
    @SerializedName("listing_price")
    @Expose
    private String listingPrice;
    @SerializedName("listing_views")
    @Expose
    private String listingViews;
    @SerializedName("specifics")
    @Expose
    private String specifics;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("contact_number")
    @Expose
    private String contactNumber;
    @SerializedName("company_name")
    @Expose
    private String companyName;
    @SerializedName("website")
    @Expose
    private String website;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("package_id")
    @Expose
    private String packageId;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("listing_status")
    @Expose
    private String listingStatus;
    @SerializedName("is_featured")
    @Expose
    private String isFeatured;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @SerializedName("deleted_at")
    @Expose
    private String deletedAt;
    @SerializedName("promotion_offer")
    @Expose
    private String promotionOffer;
    @SerializedName("offer_details")
    @Expose
    private String offerDetails;
    @SerializedName("offer_valid_start")
    @Expose
    private String offerValidStart;
    @SerializedName("offer_valid_end")
    @Expose
    private String offerValidEnd;
    @SerializedName("online")
    @Expose
    private String online;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("category_id")
    @Expose
    private String categoryId;
    @SerializedName("condition")
    @Expose
    private String condition;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("usage")
    @Expose
    private String usage;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("transmission_type")
    @Expose
    private String transmissionType;
    @SerializedName("region_specific")
    @Expose
    private String regionSpecific;
    @SerializedName("offer")
    @Expose
    private String offer;
    @SerializedName("model")
    @Expose
    private String model;
    @SerializedName("kilometers")
    @Expose
    private String kilometers;
    @SerializedName("no_of_cylinder")
    @Expose
    private String noOfCylinder;
    @SerializedName("horsepower")
    @Expose
    private String horsepower;
    @SerializedName("warranty")
    @Expose
    private String warranty;
    @SerializedName("year_of_making")
    @Expose
    private String yearOfMaking;
    @SerializedName("number_of_door")
    @Expose
    private String numberOfDoor;
    @SerializedName("rent_recurrence_payment")
    @Expose
    private String rentRecurrencePayment;
    @SerializedName("bedroom")
    @Expose
    private String bedroom;
    @SerializedName("bathroom")
    @Expose
    private String bathroom;
    @SerializedName("developers")
    @Expose
    private String developers;
    @SerializedName("available_from")
    @Expose
    private String availableFrom;
    @SerializedName("available_to")
    @Expose
    private String availableTo;
    @SerializedName("size")
    @Expose
    private String size;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("nationality")
    @Expose
    private String nationality;
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("expected_start_date")
    @Expose
    private String expectedStartDate;
    @SerializedName("degree")
    @Expose
    private String degree;
    @SerializedName("monthly_salary")
    @Expose
    private String monthlySalary;
    @SerializedName("benifits")
    @Expose
    private String benifits;
    @SerializedName("current_position")
    @Expose
    private String currentPosition;
    @SerializedName("current_company")
    @Expose
    private String currentCompany;
    @SerializedName("notice_period")
    @Expose
    private String noticePeriod;
    @SerializedName("expected_monthly_salary")
    @Expose
    private String expectedMonthlySalary;
    @SerializedName("resume_file_path")
    @Expose
    private String resumeFilePath;
    @SerializedName("body_type_id")
    @Expose
    private String bodyTypeId;
    @SerializedName("field_type_id")
    @Expose
    private String fieldTypeId;
    @SerializedName("career_level_id")
    @Expose
    private String careerLevelId;
    @SerializedName("company_field_type_id")
    @Expose
    private String companyFieldTypeId;
    @SerializedName("brand")
    @Expose
    private String brand;
    @SerializedName("maid_room")
    @Expose
    private String maidRoom;
    @SerializedName("swimming_pool")
    @Expose
    private String swimmingPool;
    @SerializedName("garden")
    @Expose
    private String garden;
    @SerializedName("private_parking")
    @Expose
    private String privateParking;
    @SerializedName("keyword")
    @Expose
    private List<String> keyword = null;
    @SerializedName("add_fav")
    @Expose
    private String addFav;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getMediaPath() {
        return mediaPath;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }

    public String getMediaThumbPath() {
        return mediaThumbPath;
    }

    public void setMediaThumbPath(String mediaThumbPath) {
        this.mediaThumbPath = mediaThumbPath;
    }

    public String getListingId() {
        return listingId;
    }

    public void setListingId(String listingId) {
        this.listingId = listingId;
    }

    public String getListingTitle() {
        return listingTitle;
    }

    public void setListingTitle(String listingTitle) {
        this.listingTitle = listingTitle;
    }

    public String getListingContent() {
        return listingContent;
    }

    public void setListingContent(String listingContent) {
        this.listingContent = listingContent;
    }

    public String getListingExpirationDate() {
        return listingExpirationDate;
    }

    public void setListingExpirationDate(String listingExpirationDate) {
        this.listingExpirationDate = listingExpirationDate;
    }

    public String getListingPrice() {
        return listingPrice;
    }

    public void setListingPrice(String listingPrice) {
        this.listingPrice = listingPrice;
    }

    public String getListingViews() {
        return listingViews;
    }

    public void setListingViews(String listingViews) {
        this.listingViews = listingViews;
    }

    public String getSpecifics() {
        return specifics;
    }

    public void setSpecifics(String specifics) {
        this.specifics = specifics;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPackageId() {
        return packageId;
    }

    public void setPackageId(String packageId) {
        this.packageId = packageId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getListingStatus() {
        return listingStatus;
    }

    public void setListingStatus(String listingStatus) {
        this.listingStatus = listingStatus;
    }

    public String getIsFeatured() {
        return isFeatured;
    }

    public void setIsFeatured(String isFeatured) {
        this.isFeatured = isFeatured;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getPromotionOffer() {
        return promotionOffer;
    }

    public void setPromotionOffer(String promotionOffer) {
        this.promotionOffer = promotionOffer;
    }

    public String getOfferDetails() {
        return offerDetails;
    }

    public void setOfferDetails(String offerDetails) {
        this.offerDetails = offerDetails;
    }

    public String getOfferValidStart() {
        return offerValidStart;
    }

    public void setOfferValidStart(String offerValidStart) {
        this.offerValidStart = offerValidStart;
    }

    public String getOfferValidEnd() {
        return offerValidEnd;
    }

    public void setOfferValidEnd(String offerValidEnd) {
        this.offerValidEnd = offerValidEnd;
    }

    public String getOnline() {
        return online;
    }

    public void setOnline(String online) {
        this.online = online;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getRegionSpecific() {
        return regionSpecific;
    }

    public void setRegionSpecific(String regionSpecific) {
        this.regionSpecific = regionSpecific;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getKilometers() {
        return kilometers;
    }

    public void setKilometers(String kilometers) {
        this.kilometers = kilometers;
    }

    public String getNoOfCylinder() {
        return noOfCylinder;
    }

    public void setNoOfCylinder(String noOfCylinder) {
        this.noOfCylinder = noOfCylinder;
    }

    public String getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(String horsepower) {
        this.horsepower = horsepower;
    }

    public String getWarranty() {
        return warranty;
    }

    public void setWarranty(String warranty) {
        this.warranty = warranty;
    }

    public String getYearOfMaking() {
        return yearOfMaking;
    }

    public void setYearOfMaking(String yearOfMaking) {
        this.yearOfMaking = yearOfMaking;
    }

    public String getNumberOfDoor() {
        return numberOfDoor;
    }

    public void setNumberOfDoor(String numberOfDoor) {
        this.numberOfDoor = numberOfDoor;
    }

    public String getRentRecurrencePayment() {
        return rentRecurrencePayment;
    }

    public void setRentRecurrencePayment(String rentRecurrencePayment) {
        this.rentRecurrencePayment = rentRecurrencePayment;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public String getBathroom() {
        return bathroom;
    }

    public void setBathroom(String bathroom) {
        this.bathroom = bathroom;
    }

    public String getDevelopers() {
        return developers;
    }

    public void setDevelopers(String developers) {
        this.developers = developers;
    }

    public String getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(String availableFrom) {
        this.availableFrom = availableFrom;
    }

    public String getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(String availableTo) {
        this.availableTo = availableTo;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getExpectedStartDate() {
        return expectedStartDate;
    }

    public void setExpectedStartDate(String expectedStartDate) {
        this.expectedStartDate = expectedStartDate;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(String monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public String getBenifits() {
        return benifits;
    }

    public void setBenifits(String benifits) {
        this.benifits = benifits;
    }

    public String getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(String currentPosition) {
        this.currentPosition = currentPosition;
    }

    public String getCurrentCompany() {
        return currentCompany;
    }

    public void setCurrentCompany(String currentCompany) {
        this.currentCompany = currentCompany;
    }

    public String getNoticePeriod() {
        return noticePeriod;
    }

    public void setNoticePeriod(String noticePeriod) {
        this.noticePeriod = noticePeriod;
    }

    public String getExpectedMonthlySalary() {
        return expectedMonthlySalary;
    }

    public void setExpectedMonthlySalary(String expectedMonthlySalary) {
        this.expectedMonthlySalary = expectedMonthlySalary;
    }

    public String getResumeFilePath() {
        return resumeFilePath;
    }

    public void setResumeFilePath(String resumeFilePath) {
        this.resumeFilePath = resumeFilePath;
    }

    public String getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(String bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }

    public String getFieldTypeId() {
        return fieldTypeId;
    }

    public void setFieldTypeId(String fieldTypeId) {
        this.fieldTypeId = fieldTypeId;
    }

    public String getCareerLevelId() {
        return careerLevelId;
    }

    public void setCareerLevelId(String careerLevelId) {
        this.careerLevelId = careerLevelId;
    }

    public String getCompanyFieldTypeId() {
        return companyFieldTypeId;
    }

    public void setCompanyFieldTypeId(String companyFieldTypeId) {
        this.companyFieldTypeId = companyFieldTypeId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMaidRoom() {
        return maidRoom;
    }

    public void setMaidRoom(String maidRoom) {
        this.maidRoom = maidRoom;
    }

    public String getSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(String swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public String getGarden() {
        return garden;
    }

    public void setGarden(String garden) {
        this.garden = garden;
    }

    public String getPrivateParking() {
        return privateParking;
    }

    public void setPrivateParking(String privateParking) {
        this.privateParking = privateParking;
    }

    public List<String> getKeyword() {
        return keyword;
    }

    public void setKeyword(List<String> keyword) {
        this.keyword = keyword;
    }

    public String getAddFav() {
        return addFav;
    }

    public void setAddFav(String addFav) {
        this.addFav = addFav;
    }
}
