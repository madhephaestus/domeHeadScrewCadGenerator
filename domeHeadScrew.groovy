import eu.mihosoft.vrl.v3d.parametrics.*;
CSG generate(){
	String type= "domeHeadScrew"
	if(args==null)
		args=["Imp8"]
	LengthParameter boltLength		= new LengthParameter("Bolt Length",10,[180,10])
	// The variable that stores the current size of this vitamin
	StringParameter size = new StringParameter(	type+" Default",args.get(0),Vitamins.listVitaminSizes(type))
	HashMap<String,Object> measurments = Vitamins.getConfiguration( type,size.getStrValue())

	def headHeightValue = measurments.headHeight
	def headDiameterValue = measurments.headDiameter
	def priceValue = measurments.price
	def massCentroidYValue = measurments.massCentroidY
	def massCentroidXValue = measurments.massCentroidX
	def sourceValue = measurments.source
	def massCentroidZValue = measurments.massCentroidZ
	def massKgValue = measurments.massKg
	def boltDiameterValue = measurments.boltDiameter
	println "Measurment headHeightValue =  "+headHeightValue
	println "Measurment headDiameterValue =  "+headDiameterValue
	println "Measurment priceValue =  "+priceValue
	println "Measurment massCentroidYValue =  "+massCentroidYValue
	println "Measurment massCentroidXValue =  "+massCentroidXValue
	println "Measurment sourceValue =  "+sourceValue
	println "Measurment massCentroidZValue =  "+massCentroidZValue
	println "Measurment massKgValue =  "+massKgValue
	println "Measurment boltDiameterValue =  "+boltDiameterValue
	Parabola.coneByHeight(10, 20)
	// Stub of a CAD object
	CSG part = Parabola.coneByHeight(headDiameterValue/2, headHeightValue)
				.rotx(90)
				.toZMin()
				.union(new Cylinder(boltDiameterValue/2,boltLength.getMM()).toCSG().toZMax())
				
	return part
		.setParameter(size)
		.setRegenerate({generate()})
}
return generate() 