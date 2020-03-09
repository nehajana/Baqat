
package shubham.com.baqat.HarshitCreateAdd.ApiModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("condition")
    @Expose
    private Condition condition;
    @SerializedName("type")
    @Expose
    private Type type;
    @SerializedName("usage")
    @Expose
    private Usage usage;
    @SerializedName("color")
    @Expose
    private Color color;
    @SerializedName("transmission_type")
    @Expose
    private TransmissionType transmissionType;
    @SerializedName("region_specific")
    @Expose
    private RegionSpecific regionSpecific;
    @SerializedName("offer")
    @Expose
    private Offer offer;
    @SerializedName("model")
    @Expose
    private Model model;
    @SerializedName("kilometers")
    @Expose
    private Kilometers kilometers;
    @SerializedName("no_of_cylinder")
    @Expose
    private NoOfCylinder noOfCylinder;
    @SerializedName("horsepower")
    @Expose
    private Horsepower horsepower;
    @SerializedName("warranty")
    @Expose
    private Warranty warranty;
    @SerializedName("year_of_making")
    @Expose
    private YearOfMaking yearOfMaking;
    @SerializedName("number_of_door")
    @Expose
    private NumberOfDoor numberOfDoor;
    @SerializedName("rent_recurrence_payment")
    @Expose
    private RentRecurrencePayment rentRecurrencePayment;
    @SerializedName("bedroom")
    @Expose
    private Bedroom bedroom;
    @SerializedName("bathroom")
    @Expose
    private Bathroom bathroom;
    @SerializedName("developers")
    @Expose
    private Developers developers;
    @SerializedName("status")
    @Expose
    private Status status;
    @SerializedName("available_from")
    @Expose
    private AvailableFrom availableFrom;
    @SerializedName("available_to")
    @Expose
    private AvailableTo availableTo;
    @SerializedName("size")
    @Expose
    private Size size;
    @SerializedName("gender")
    @Expose
    private Gender gender;
    @SerializedName("nationality")
    @Expose
    private Nationality nationality;
    @SerializedName("position")
    @Expose
    private Position position;
    @SerializedName("expected_start_date")
    @Expose
    private ExpectedStartDate expectedStartDate;
    @SerializedName("degree")
    @Expose
    private Degree degree;
    @SerializedName("monthly_salary")
    @Expose
    private MonthlySalary monthlySalary;
    @SerializedName("benifits")
    @Expose
    private Benifits benifits;
    @SerializedName("current_position")
    @Expose
    private CurrentPosition currentPosition;
    @SerializedName("current_company")
    @Expose
    private CurrentCompany currentCompany;
    @SerializedName("notice_period")
    @Expose
    private NoticePeriod noticePeriod;
    @SerializedName("expected_monthly_salary")
    @Expose
    private ExpectedMonthlySalary expectedMonthlySalary;
    @SerializedName("resume_file_path")
    @Expose
    private ResumeFilePath resumeFilePath;
    @SerializedName("body_type_id")
    @Expose
    private BodyTypeId bodyTypeId;
    @SerializedName("field_type_id")
    @Expose
    private FieldTypeId fieldTypeId;
    @SerializedName("career_level_id")
    @Expose
    private CareerLevelId careerLevelId;
    @SerializedName("company_field_type_id")
    @Expose
    private CompanyFieldTypeId companyFieldTypeId;
    @SerializedName("brand")
    @Expose
    private Brand brand;
    @SerializedName("maid_room")
    @Expose
    private MaidRoom maidRoom;
    @SerializedName("swimming_pool")
    @Expose
    private SwimmingPool swimmingPool;
    @SerializedName("garden")
    @Expose
    private Garden garden;
    @SerializedName("private_parking")
    @Expose
    private PrivateParking privateParking;

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Usage getUsage() {
        return usage;
    }

    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public RegionSpecific getRegionSpecific() {
        return regionSpecific;
    }

    public void setRegionSpecific(RegionSpecific regionSpecific) {
        this.regionSpecific = regionSpecific;
    }

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Kilometers getKilometers() {
        return kilometers;
    }

    public void setKilometers(Kilometers kilometers) {
        this.kilometers = kilometers;
    }

    public NoOfCylinder getNoOfCylinder() {
        return noOfCylinder;
    }

    public void setNoOfCylinder(NoOfCylinder noOfCylinder) {
        this.noOfCylinder = noOfCylinder;
    }

    public Horsepower getHorsepower() {
        return horsepower;
    }

    public void setHorsepower(Horsepower horsepower) {
        this.horsepower = horsepower;
    }

    public Warranty getWarranty() {
        return warranty;
    }

    public void setWarranty(Warranty warranty) {
        this.warranty = warranty;
    }

    public YearOfMaking getYearOfMaking() {
        return yearOfMaking;
    }

    public void setYearOfMaking(YearOfMaking yearOfMaking) {
        this.yearOfMaking = yearOfMaking;
    }

    public NumberOfDoor getNumberOfDoor() {
        return numberOfDoor;
    }

    public void setNumberOfDoor(NumberOfDoor numberOfDoor) {
        this.numberOfDoor = numberOfDoor;
    }

    public RentRecurrencePayment getRentRecurrencePayment() {
        return rentRecurrencePayment;
    }

    public void setRentRecurrencePayment(RentRecurrencePayment rentRecurrencePayment) {
        this.rentRecurrencePayment = rentRecurrencePayment;
    }

    public Bedroom getBedroom() {
        return bedroom;
    }

    public void setBedroom(Bedroom bedroom) {
        this.bedroom = bedroom;
    }

    public Bathroom getBathroom() {
        return bathroom;
    }

    public void setBathroom(Bathroom bathroom) {
        this.bathroom = bathroom;
    }

    public Developers getDevelopers() {
        return developers;
    }

    public void setDevelopers(Developers developers) {
        this.developers = developers;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public AvailableFrom getAvailableFrom() {
        return availableFrom;
    }

    public void setAvailableFrom(AvailableFrom availableFrom) {
        this.availableFrom = availableFrom;
    }

    public AvailableTo getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(AvailableTo availableTo) {
        this.availableTo = availableTo;
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Nationality getNationality() {
        return nationality;
    }

    public void setNationality(Nationality nationality) {
        this.nationality = nationality;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public ExpectedStartDate getExpectedStartDate() {
        return expectedStartDate;
    }

    public void setExpectedStartDate(ExpectedStartDate expectedStartDate) {
        this.expectedStartDate = expectedStartDate;
    }

    public Degree getDegree() {
        return degree;
    }

    public void setDegree(Degree degree) {
        this.degree = degree;
    }

    public MonthlySalary getMonthlySalary() {
        return monthlySalary;
    }

    public void setMonthlySalary(MonthlySalary monthlySalary) {
        this.monthlySalary = monthlySalary;
    }

    public Benifits getBenifits() {
        return benifits;
    }

    public void setBenifits(Benifits benifits) {
        this.benifits = benifits;
    }

    public CurrentPosition getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(CurrentPosition currentPosition) {
        this.currentPosition = currentPosition;
    }

    public CurrentCompany getCurrentCompany() {
        return currentCompany;
    }

    public void setCurrentCompany(CurrentCompany currentCompany) {
        this.currentCompany = currentCompany;
    }

    public NoticePeriod getNoticePeriod() {
        return noticePeriod;
    }

    public void setNoticePeriod(NoticePeriod noticePeriod) {
        this.noticePeriod = noticePeriod;
    }

    public ExpectedMonthlySalary getExpectedMonthlySalary() {
        return expectedMonthlySalary;
    }

    public void setExpectedMonthlySalary(ExpectedMonthlySalary expectedMonthlySalary) {
        this.expectedMonthlySalary = expectedMonthlySalary;
    }

    public ResumeFilePath getResumeFilePath() {
        return resumeFilePath;
    }

    public void setResumeFilePath(ResumeFilePath resumeFilePath) {
        this.resumeFilePath = resumeFilePath;
    }

    public BodyTypeId getBodyTypeId() {
        return bodyTypeId;
    }

    public void setBodyTypeId(BodyTypeId bodyTypeId) {
        this.bodyTypeId = bodyTypeId;
    }

    public FieldTypeId getFieldTypeId() {
        return fieldTypeId;
    }

    public void setFieldTypeId(FieldTypeId fieldTypeId) {
        this.fieldTypeId = fieldTypeId;
    }

    public CareerLevelId getCareerLevelId() {
        return careerLevelId;
    }

    public void setCareerLevelId(CareerLevelId careerLevelId) {
        this.careerLevelId = careerLevelId;
    }

    public CompanyFieldTypeId getCompanyFieldTypeId() {
        return companyFieldTypeId;
    }

    public void setCompanyFieldTypeId(CompanyFieldTypeId companyFieldTypeId) {
        this.companyFieldTypeId = companyFieldTypeId;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public MaidRoom getMaidRoom() {
        return maidRoom;
    }

    public void setMaidRoom(MaidRoom maidRoom) {
        this.maidRoom = maidRoom;
    }

    public SwimmingPool getSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(SwimmingPool swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public Garden getGarden() {
        return garden;
    }

    public void setGarden(Garden garden) {
        this.garden = garden;
    }

    public PrivateParking getPrivateParking() {
        return privateParking;
    }

    public void setPrivateParking(PrivateParking privateParking) {
        this.privateParking = privateParking;
    }

}
