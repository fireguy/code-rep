@RestController
class ThisWillActuallyRun { 
	@RequestMapping("/") 
	String home() {
		"Hello World!" 
	}
}

//>spring run app.groovy
