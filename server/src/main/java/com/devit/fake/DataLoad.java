package com.devit.fake;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.devit.model.Region;
import com.devit.model.City;
import com.devit.model.Monument;
import com.devit.model.Role;
import com.devit.model.User;
import com.devit.repository.RoleRepository;
import com.devit.service.ISpotService;
import com.devit.service.ITourismService;
import com.devit.service.IUserService;

@Component("dataload")
public class DataLoad{

	
	@Autowired
	public IUserService userService;
	@Autowired
	public ITourismService tourismService;
	@Autowired
	public ISpotService spotService;
	
  
	public void loadCity() {

		City city2 = new City("Al Hoceima",0,0,"http://businessinfo.ma/wp-content/uploads/2015/10/Al-Hoceima.jpg");
		City city3 = new City("Rabat",0,0,"https://c1.staticflickr.com/5/4151/5174296658_7c15010a9b_b.jpg");
		City city4 = new City("Marrakech",0,0,"http://wallsdesk.com/wp-content/uploads/2017/01/Marrakech-High-Quality-Wallpapers.jpg");
		City city1 = new City("Casablanca",0,0,"http://www.e-consulting.ma/wp-content/uploads/2016/11/agence-web-casablanca.jpg");
		City city5 = new City("Tanger",0,0,"https://c1.staticflickr.com/4/3036/2920129470_f2932bea12_b.jpg");
		
		spotService.saveCity(city2);
		spotService.saveCity(city3);
		spotService.saveCity(city1);
		spotService.saveCity(city4);
		spotService.saveCity(city5);
		
	}
	
	public void loadRegion() {

		Region region1 = new Region("Grand Casablanca","http://casablanca.ma/RS/Img/Population-Wilaya-Casablanca-Settat.jpg");
		Region region2 = new Region("Taza-Al Hoceïma-Taounate","http://www.ads.ma/uploads/RTEmagicC_r%C3%A9gion_hoceima.bmp.jpg");
		Region region3 = new Region("Tanger-Tétouan","http://lpe-asso.org/wp-content/uploads/adherents/pierrelouis/2015/04/Nouvelle-Carte-des-Regions-du-Maroc-2015.jpg");
		Region region4 = new Region("Marrakech-Tensift-Al Haouz","http://www.equipement.gov.ma/Carte-Region/RegionMarrakech/Presentation-de-la-region/Monographie/PublishingImages/monographie%20region.PNG");
		Region region5 = new Region("Rabat-Salé-Zemmour-Zaër","http://ma.chm-cbd.net/rabat-sale-kenitra/images/chm-map.png");
		
		spotService.saveRegion(region1);
		spotService.saveRegion(region2);
		spotService.saveRegion(region3);
		spotService.saveRegion(region4);
		spotService.saveRegion(region5);
	}
	
	public void loadMonument(){
		
		Monument adouz = new Monument("Adouz","Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc posuere tortor tincidunt arcu vestibulum feugiat. Mauris aliquam sodales ligula nec mattis. Vivamus lectus massa, maximus eget tellus eget, placerat condimentum elit. Mauris auctor euismod felis sit amet dignissim. Ut porta est ultrices efficitur ultricies. Cras mollis fringilla velit non iaculis. Sed nec ullamcorper augue. Suspendisse lobortis convallis lorem, eget mattis mi ullamcorper vel. Nullam pellentesque suscipit luctus. Ut tempus odio quis eros euismod gravida. Quisque eu nunc tellus. Sed ut dictum metus. Morbi tortor purus, venenatis nec lacinia sed, suscipit nec quam. Morbi fringilla dictum quam quis suscipit. ",35.153972,-4.325262,"https://i.ytimg.com/vi/rVBfz7fMW2k/maxresdefault.jpg");
		Monument torres = new Monument("Torres de Alcala","In nibh magna, rutrum vestibulum eros sed, aliquam lacinia velit. Suspendisse fermentum, lacus non ultrices venenatis, arcu nibh tristique nisl, nec luctus purus augue in risus. Sed nec nulla id lacus volutpat interdum. Donec eleifend ligula augue, at iaculis est ullamcorper eu. Nunc eu hendrerit urna. Donec nisi metus, accumsan quis justo sit amet, laoreet tincidunt eros. Mauris ut magna quis nulla blandit eleifend. Donec rhoncus mi et nisi accumsan condimentum. Donec bibendum ex a felis blandit sodales. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Pellentesque semper a ex tempor pellentesque. Ut sed neque ligula. Nulla mattis eleifend vehicula. Donec ullamcorper elementum dolor, auctor consectetur nisi molestie id. Etiam aliquet orci ut elit interdum semper. Nunc finibus urna sit amet nisi iaculis finibus.",35.153972,-4.325262,"https://c1.staticflickr.com/3/2810/11805954674_52e57cc07b_b.jpg");
		Monument bades = new Monument("Ile de Bades","Maecenas pellentesque, nisl et maximus elementum, enim purus mattis ipsum, quis tempor nulla est in tellus. Phasellus ut lacus enim. Maecenas consectetur semper ex, at mattis felis volutpat in. Proin scelerisque, lorem eget sollicitudin cursus, neque tortor dapibus diam, sed gravida augue neque quis sapien. Aliquam interdum nisi ipsum, sed malesuada tortor aliquet vitae. Praesent tellus justo, iaculis in cursus non, gravida eu sem. Vestibulum tortor ante, porta non eleifend in, blandit quis est. Vestibulum ut orci scelerisque elit malesuada elementum id eu lorem. Suspendisse a tortor lectus. ",35.153972,-4.325262,"http://www.maroc-trip.com/themes/images_maroc/nsdnqw2296000000_l.jpg");
		Monument nekour = new Monument("Ile Al Nekour","Donec erat nisi, laoreet a sagittis non, hendrerit ac lorem. Etiam cursus, lectus in mattis ornare, massa lacus commodo lacus, nec hendrerit lectus neque in ante. Vivamus quis diam feugiat, vestibulum lectus vel, pharetra sem. Mauris in pellentesque nisl. Integer quis ullamcorper justo. Pellentesque quis facilisis tortor, et dapibus augue. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Mauris suscipit lorem sagittis lorem sollicitudin, a cursus nisi porta. Duis bibendum nulla ac eros pellentesque, ac pharetra velit eleifend. Ut at dolor eleifend, fermentum metus nec, aliquet ex. Pellentesque finibus luctus ipsum ac consequat. Sed pulvinar ipsum vitae felis tempor elementum. ",35.153972,-4.325262,"http://static.panoramio.com/photos/large/81456313.jpg");
		Monument calariris = new Monument("La baie de CALA IRIS","Sed ex justo, hendrerit imperdiet ipsum sit amet, tincidunt venenatis velit. Nullam quis purus eu ante luctus volutpat. Suspendisse mattis mi lorem, porttitor interdum sem euismod vel. In hac habitasse platea dictumst. Nullam a tempor ligula. Sed volutpat diam vel erat malesuada condimentum. Pellentesque pretium ante at lectus tempor, placerat sagittis dui consequat. Nunc tincidunt, risus sit amet lacinia interdum, ex enim ullamcorper risus, tincidunt ornare ipsum nunc sed tortor. Nunc at eros vulputate, ullamcorper augue vitae, ullamcorper quam. ",35.153972,-4.325262,"https://i.imgur.com/b7LtVeP.jpg");
		Monument quemado = new Monument("Plage QUEMADO","Praesent feugiat ac eros a vehicula. Donec metus ex, volutpat in commodo et, efficitur eu tellus. Vestibulum eu mi non diam feugiat cursus. Pellentesque enim sem, pretium vel malesuada eget, condimentum quis justo. Donec feugiat augue urna, ut malesuada elit convallis sit amet. Proin tellus dui, imperdiet eget vestibulum eu, interdum eu nisl. Maecenas ornare tortor nec quam eleifend, nec porttitor elit posuere. In et massa sit amet risus suscipit accumsan in eu arcu. Vivamus tincidunt dapibus lorem, dignissim fringilla mi malesuada ac. Ut venenatis nisl id porttitor rutrum. ",35.153972,-4.325262,"https://mw2.google.com/mw-panoramio/photos/medium/78683727.jpg");

		adouz.setCity(spotService.findCityByName("Al Hoceima"));
		torres.setCity(spotService.findCityByName("Al Hoceima"));
		bades.setCity(spotService.findCityByName("Al Hoceima"));
		nekour.setCity(spotService.findCityByName("Al Hoceima"));
		calariris.setCity(spotService.findCityByName("Al Hoceima"));
		quemado.setCity(spotService.findCityByName("Al Hoceima"));
		
		adouz.setRegion(spotService.findRegionByName("Taza-Al Hoceïma-Taounate"));	
		torres.setRegion(spotService.findRegionByName("Taza-Al Hoceïma-Taounate"));	
		bades.setRegion(spotService.findRegionByName("Taza-Al Hoceïma-Taounate"));	
		nekour.setRegion(spotService.findRegionByName("Taza-Al Hoceïma-Taounate"));	
		calariris.setRegion(spotService.findRegionByName("Taza-Al Hoceïma-Taounate"));	
		quemado.setRegion(spotService.findRegionByName("Taza-Al Hoceïma-Taounate"));	
		
		Monument monument1 = new Monument("Kasbah des Oudayas","Nunc augue diam, sodales quis imperdiet bibendum, molestie maximus ante. Ut vitae odio ac elit pulvinar convallis ac dignissim odio. Praesent justo nisi, luctus sed congue cursus, molestie in sapien. Nulla facilisi. Quisque maximus purus ac est tempor feugiat. In posuere ante non massa dapibus, vel mollis leo posuere. Proin fermentum feugiat ipsum, eu rhoncus risus feugiat a. Ut dictum ante velit, commodo fermentum ante iaculis a. Vivamus nec eros sit amet orci ultricies semper et porta nisl. Curabitur quam quam, sagittis vitae rutrum ac, iaculis eget elit. ",0,0,"https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Tourists_posing_at_the_National_Monument_of_Scotland.jpg/1200px-Tourists_posing_at_the_National_Monument_of_Scotland.jpg");
		Monument monument2 = new Monument("Tour Hassan","In tristique urna imperdiet neque vestibulum, non fringilla lectus volutpat. Quisque porttitor luctus lorem vel vulputate. Nam condimentum, nunc at sodales rutrum, purus nunc vulputate dui, in rutrum dolor augue et nulla. Duis sodales sed tortor quis pulvinar. In ipsum quam, luctus sit amet nibh vel, scelerisque fringilla quam. Morbi ante odio, ultrices laoreet laoreet in, rhoncus quis enim. Nulla condimentum arcu in condimentum sagittis. Donec in vestibulum elit. Curabitur tincidunt ac mauris eget tincidunt. Phasellus porttitor at lacus vel elementum. Quisque porttitor luctus ligula, eu faucibus dolor tempor vitae. Integer cursus pretium lacus, rhoncus feugiat lacus finibus sed. Vestibulum lectus lectus, semper in tincidunt vel, fermentum in augue. Maecenas sem nunc, lobortis et nisl eu, venenatis tempor ligula. ",0,0,"https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Tourists_posing_at_the_National_Monument_of_Scotland.jpg/1200px-Tourists_posing_at_the_National_Monument_of_Scotland.jpg");
		Monument monument3 = new Monument("Mausolée Mohammed-V","In tincidunt in odio et vestibulum. Aliquam hendrerit, eros ac ornare pulvinar, ipsum ante mollis ex, non tincidunt nisi turpis et neque. Vivamus sit amet efficitur urna, a aliquet arcu. Fusce vel bibendum risus. Vestibulum quis consequat sem. Nunc suscipit ante sit amet sagittis hendrerit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. ",0,0,"https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Tourists_posing_at_the_National_Monument_of_Scotland.jpg/1200px-Tourists_posing_at_the_National_Monument_of_Scotland.jpg");
		Monument monument4 = new Monument("Exotic Gardens of Rabat Sale","Nunc non ligula massa. Nulla lectus sapien, porta vel gravida fermentum, fermentum a tellus. Nam non arcu nec turpis venenatis volutpat. Curabitur vitae dui et magna tristique rutrum. Sed rhoncus lorem risus, vel auctor enim eleifend eu. Vestibulum ultricies elementum blandit. Curabitur vitae eleifend justo. Ut nec libero viverra, finibus est ac, lobortis enim. Proin est quam, imperdiet quis diam id, venenatis gravida leo. Morbi imperdiet ut est in facilisis. Nunc at ullamcorper metus. Suspendisse iaculis fermentum maximus. Proin nec quam quis mi elementum faucibus at ac metus. ",0,0,"https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Tourists_posing_at_the_National_Monument_of_Scotland.jpg/1200px-Tourists_posing_at_the_National_Monument_of_Scotland.jpg");
		
		monument1.setCity(spotService.findCityByName("Rabat"));
		monument2.setCity(spotService.findCityByName("Rabat"));
		monument3.setCity(spotService.findCityByName("Rabat"));
		monument4.setCity(spotService.findCityByName("Rabat"));
		
		monument1.setRegion(spotService.findRegionByName("Rabat-Salé-Zemmour-Zaër"));
		monument2.setRegion(spotService.findRegionByName("Rabat-Salé-Zemmour-Zaër"));
		monument3.setRegion(spotService.findRegionByName("Rabat-Salé-Zemmour-Zaër"));
		monument4.setRegion(spotService.findRegionByName("Rabat-Salé-Zemmour-Zaër"));
		
		Monument monument5 = new Monument("Place Jemaa el-Fna","Nunc augue diam, sodales quis imperdiet bibendum, molestie maximus ante. Ut vitae odio ac elit pulvinar convallis ac dignissim odio. Praesent justo nisi, luctus sed congue cursus, molestie in sapien. Nulla facilisi. Quisque maximus purus ac est tempor feugiat. In posuere ante non massa dapibus, vel mollis leo posuere. Proin fermentum feugiat ipsum, eu rhoncus risus feugiat a. Ut dictum ante velit, commodo fermentum ante iaculis a. Vivamus nec eros sit amet orci ultricies semper et porta nisl. Curabitur quam quam, sagittis vitae rutrum ac, iaculis eget elit. ",0,0,"https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Tourists_posing_at_the_National_Monument_of_Scotland.jpg/1200px-Tourists_posing_at_the_National_Monument_of_Scotland.jpg");
		Monument monument6 = new Monument("Jardin Majorelle","In tristique urna imperdiet neque vestibulum, non fringilla lectus volutpat. Quisque porttitor luctus lorem vel vulputate. Nam condimentum, nunc at sodales rutrum, purus nunc vulputate dui, in rutrum dolor augue et nulla. Duis sodales sed tortor quis pulvinar. In ipsum quam, luctus sit amet nibh vel, scelerisque fringilla quam. Morbi ante odio, ultrices laoreet laoreet in, rhoncus quis enim. Nulla condimentum arcu in condimentum sagittis. Donec in vestibulum elit. Curabitur tincidunt ac mauris eget tincidunt. Phasellus porttitor at lacus vel elementum. Quisque porttitor luctus ligula, eu faucibus dolor tempor vitae. Integer cursus pretium lacus, rhoncus feugiat lacus finibus sed. Vestibulum lectus lectus, semper in tincidunt vel, fermentum in augue. Maecenas sem nunc, lobortis et nisl eu, venenatis tempor ligula. ",0,0,"https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Tourists_posing_at_the_National_Monument_of_Scotland.jpg/1200px-Tourists_posing_at_the_National_Monument_of_Scotland.jpg");
		Monument monument7 = new Monument("Ménara","In tincidunt in odio et vestibulum. Aliquam hendrerit, eros ac ornare pulvinar, ipsum ante mollis ex, non tincidunt nisi turpis et neque. Vivamus sit amet efficitur urna, a aliquet arcu. Fusce vel bibendum risus. Vestibulum quis consequat sem. Nunc suscipit ante sit amet sagittis hendrerit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. ",0,0,"https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Tourists_posing_at_the_National_Monument_of_Scotland.jpg/1200px-Tourists_posing_at_the_National_Monument_of_Scotland.jpg");
		Monument monument8 = new Monument("Bab Agnaou","Nunc non ligula massa. Nulla lectus sapien, porta vel gravida fermentum, fermentum a tellus. Nam non arcu nec turpis venenatis volutpat. Curabitur vitae dui et magna tristique rutrum. Sed rhoncus lorem risus, vel auctor enim eleifend eu. Vestibulum ultricies elementum blandit. Curabitur vitae eleifend justo. Ut nec libero viverra, finibus est ac, lobortis enim. Proin est quam, imperdiet quis diam id, venenatis gravida leo. Morbi imperdiet ut est in facilisis. Nunc at ullamcorper metus. Suspendisse iaculis fermentum maximus. Proin nec quam quis mi elementum faucibus at ac metus. ",0,0,"https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Tourists_posing_at_the_National_Monument_of_Scotland.jpg/1200px-Tourists_posing_at_the_National_Monument_of_Scotland.jpg");
		
		monument5.setCity(spotService.findCityByName("Marrakech"));
		monument6.setCity(spotService.findCityByName("Marrakech"));
		monument7.setCity(spotService.findCityByName("Marrakech"));
		monument8.setCity(spotService.findCityByName("Marrakech"));
		
		monument5.setRegion(spotService.findRegionByName("Marrakech-Tensift-Al Haouz"));
		monument6.setRegion(spotService.findRegionByName("Marrakech-Tensift-Al Haouz"));
		monument7.setRegion(spotService.findRegionByName("Marrakech-Tensift-Al Haouz"));
		monument8.setRegion(spotService.findRegionByName("Marrakech-Tensift-Al Haouz"));
		
		Monument monument9 = new Monument("Mosquée Hassan II","Nunc augue diam, sodales quis imperdiet bibendum, molestie maximus ante. Ut vitae odio ac elit pulvinar convallis ac dignissim odio. Praesent justo nisi, luctus sed congue cursus, molestie in sapien. Nulla facilisi. Quisque maximus purus ac est tempor feugiat. In posuere ante non massa dapibus, vel mollis leo posuere. Proin fermentum feugiat ipsum, eu rhoncus risus feugiat a. Ut dictum ante velit, commodo fermentum ante iaculis a. Vivamus nec eros sit amet orci ultricies semper et porta nisl. Curabitur quam quam, sagittis vitae rutrum ac, iaculis eget elit. ",0,0,"https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Tourists_posing_at_the_National_Monument_of_Scotland.jpg/1200px-Tourists_posing_at_the_National_Monument_of_Scotland.jpg");
		
		monument9.setCity(spotService.findCityByName("Casablanca"));
		monument9.setRegion(spotService.findRegionByName("Grand Casablanca"));
		
		Monument monument10 = new Monument("Grottes d'Hercule","In tristique urna imperdiet neque vestibulum, non fringilla lectus volutpat. Quisque porttitor luctus lorem vel vulputate. Nam condimentum, nunc at sodales rutrum, purus nunc vulputate dui, in rutrum dolor augue et nulla. Duis sodales sed tortor quis pulvinar. In ipsum quam, luctus sit amet nibh vel, scelerisque fringilla quam. Morbi ante odio, ultrices laoreet laoreet in, rhoncus quis enim. Nulla condimentum arcu in condimentum sagittis. Donec in vestibulum elit. Curabitur tincidunt ac mauris eget tincidunt. Phasellus porttitor at lacus vel elementum. Quisque porttitor luctus ligula, eu faucibus dolor tempor vitae. Integer cursus pretium lacus, rhoncus feugiat lacus finibus sed. Vestibulum lectus lectus, semper in tincidunt vel, fermentum in augue. Maecenas sem nunc, lobortis et nisl eu, venenatis tempor ligula. ",0,0,"https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Tourists_posing_at_the_National_Monument_of_Scotland.jpg/1200px-Tourists_posing_at_the_National_Monument_of_Scotland.jpg");
		Monument monument11 = new Monument("Église anglicane Saint André","In tincidunt in odio et vestibulum. Aliquam hendrerit, eros ac ornare pulvinar, ipsum ante mollis ex, non tincidunt nisi turpis et neque. Vivamus sit amet efficitur urna, a aliquet arcu. Fusce vel bibendum risus. Vestibulum quis consequat sem. Nunc suscipit ante sit amet sagittis hendrerit. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. ",0,0,"https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Tourists_posing_at_the_National_Monument_of_Scotland.jpg/1200px-Tourists_posing_at_the_National_Monument_of_Scotland.jpg");
		Monument monument12 = new Monument("Plage Achakkar","Nunc non ligula massa. Nulla lectus sapien, porta vel gravida fermentum, fermentum a tellus. Nam non arcu nec turpis venenatis volutpat. Curabitur vitae dui et magna tristique rutrum. Sed rhoncus lorem risus, vel auctor enim eleifend eu. Vestibulum ultricies elementum blandit. Curabitur vitae eleifend justo. Ut nec libero viverra, finibus est ac, lobortis enim. Proin est quam, imperdiet quis diam id, venenatis gravida leo. Morbi imperdiet ut est in facilisis. Nunc at ullamcorper metus. Suspendisse iaculis fermentum maximus. Proin nec quam quis mi elementum faucibus at ac metus. ",0,0,"https://upload.wikimedia.org/wikipedia/commons/thumb/2/21/Tourists_posing_at_the_National_Monument_of_Scotland.jpg/1200px-Tourists_posing_at_the_National_Monument_of_Scotland.jpg");
		
		monument10.setCity(spotService.findCityByName("Tanger"));
		monument11.setCity(spotService.findCityByName("Tanger"));
		monument12.setCity(spotService.findCityByName("Tanger"));
		
		monument10.setRegion(spotService.findRegionByName("Tanger-Tétouan"));
		monument11.setRegion(spotService.findRegionByName("Tanger-Tétouan"));
		monument12.setRegion(spotService.findRegionByName("Tanger-Tétouan"));
		
		tourismService.saveMonument(adouz, "mail@test");
		tourismService.saveMonument(torres, "mail@test");
		tourismService.saveMonument(bades, "mail@test");
		tourismService.saveMonument(nekour, "mail@test");
		tourismService.saveMonument(calariris, "mail@test");
		tourismService.saveMonument(quemado, "mail@test");
		tourismService.saveMonument(monument1, "mail@test");
		tourismService.saveMonument(monument2, "mail@test");
		tourismService.saveMonument(monument3, "mail@test");
		tourismService.saveMonument(monument4, "mail@test");
		tourismService.saveMonument(monument5, "mail@test");
		tourismService.saveMonument(monument6, "mail@test");
		tourismService.saveMonument(monument7, "mail@test");
		tourismService.saveMonument(monument8, "mail@test");
		tourismService.saveMonument(monument9, "mail@test");
		tourismService.saveMonument(monument10, "mail@test");
		tourismService.saveMonument(monument11, "mail@test");
		tourismService.saveMonument(monument12, "mail@test");
		
	  }
	
}
