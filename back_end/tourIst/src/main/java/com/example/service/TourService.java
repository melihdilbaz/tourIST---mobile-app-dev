package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.ItineraryException;
import com.example.model.Category;
import com.example.model.Destination;
import com.example.model.Itinerary;
import com.example.repo.CategoryRepo;
import com.example.repo.DestinationRepo;
import com.example.repo.ItineraryRepo;

import jakarta.annotation.PostConstruct;

@Service
public class TourService {

	
	@Autowired CategoryRepo categoryRepo;
	
	@Autowired DestinationRepo destRepo;
	
	@Autowired ItineraryRepo itineraryRepo;


	@PostConstruct
	public void init() {
		if (categoryRepo.count() == 0) {
			Category cat1 = new Category("Church");
			Category cat2 = new Category("Park");
			Category cat3 = new Category("Mosque");
			Category cat4 = new Category("Palace");
			
			cat1= categoryRepo.save(cat1);
			cat2 = categoryRepo.save(cat2);
			cat3 = categoryRepo.save(cat3);
			cat4 = categoryRepo.save(cat4);
			
			if (destRepo.count() < 12) {
				String text1 = "Currently the largest mosque in Turkey, the Grand Çamlıca Mosque (Turkish pronunciation: [tʃamlɯdʒa]) (Turkish: Büyük Çamlıca Camii) is a landmark complex for Islamic worship which was completed and opened on 7 March 2019. The mosque stands astride Çamlıca Hill in the Üsküdar district of Istanbul and is visible from much of the centre of the city. The complex incorporates an art gallery, library, and conference hall.[2] It can hold up to 63,000 worshippers at a time (can accommodate up to 100,000 people in case of an earthquake).";
				String link1 = "https://en.wikipedia.org/wiki/%C3%87aml%C4%B1ca_Mosque";
				String text2 = "The Topkapı Palace (Turkish: Topkapı Sarayı; Ottoman Turkish: طوپقپو سرايى, romanized: ṭopḳapu sarāyı, lit. 'cannon gate palace'), or the Seraglio, is a large museum and library in the east of the Fatih district of Istanbul in Turkey. From the 1460s to the completion of Dolmabahçe Palace in 1856, it served as the administrative center of the Ottoman Empire, and was the main residence of its sultans.";
				String link2 = "https://en.wikipedia.org/wiki/Topkap%C4%B1_Palace";
				String text3 = "The Beylerbeyi Palace (Turkish: Beylerbeyi Sarayı, literally meaning the palace of the bey of beys) is located in the Beylerbeyi neighbourhood of Üsküdar district in Istanbul, Turkey, at the Asian side of the Bosphorus. An imperial Ottoman summer residence built between 1861 and 1865, it is now situated immediately north of the first Bosphorus Bridge. It was the last place where Sultan Abdulhamid II was under house arrest before his death in 1918.";
				String link3 = "https://en.wikipedia.org/wiki/Beylerbeyi_Palace";
				String text4 = "Çırağan Palace (Turkish: Çırağan Sarayı), a former Ottoman palace, is now a five-star hotel in the Kempinski Hotels chain. It is located on the European shore of the Bosporus, between Beşiktaş and Ortaköy in Istanbul, Turkey.\r\n"
						+ "\r\n"
						+ "The Sultan's Suite, billed at US$35,419.68 per night, is ranked number 14 on World's 15 most expensive hotel suites compiled by CNN Go in 2012.";
				String link4 = "https://en.wikipedia.org/wiki/%C3%87%C4%B1ra%C4%9Fan_Palace";
				String text5 = "The Süleymaniye Mosque (Turkish: Süleymaniye Camii, pronounced [sylejˈmaːnije]) is an Ottoman imperial mosque located on the Third Hill of Istanbul, Turkey. The mosque was commissioned by Suleiman the Magnificent (r. 1520–1566) and designed by the imperial architect Mimar Sinan. An inscription specifies the foundation date as 1550 and the inauguration date as 1557, although work on the complex probably continued for a few years after this.";
				String link5 = "https://en.wikipedia.org/wiki/S%C3%BCleymaniye_Mosque";
				String text6 = "Hagia Sophia (lit. 'Holy Wisdom'; Turkish: Ayasofya; Greek: Ἁγία Σοφία, romanized: Hagía Sofía; Latin: Sancta Sapientia), officially the Hagia Sophia Grand Mosque (Turkish: Ayasofya-i Kebir Cami-i Şerifi), is a mosque, a former church, and a major cultural and historical site in Istanbul, Turkey. The last of three church buildings to be successively erected on the site by the Eastern Roman Empire, it was completed in 537 AD. The site was an Eastern Orthodox church from 360 AD to 1204, when it was converted to a Catholic church following the Fourth Crusade. It was reclaimed in 1261 and remained Eastern Orthodox until the Ottoman conquest of Constantinople in 1453. It served as a mosque until 1935, when it became a museum. In 2020, the site once again became a mosque.";
				String link6 = "https://en.wikipedia.org/wiki/Hagia_Sophia";
				
				String text7 = "The Bulgarian St. Stephen Church (Bulgarian: Църква \"Свети Стефан\"; Turkish: Sveti Stefan Kilisesi), also known as the Bulgarian Iron Church, is a Bulgarian Orthodox church in Balat, Istanbul, Turkey. It is famous for being made of prefabricated cast iron elements in the Neo-Byzantine style. The church belongs to the Bulgarian Christian minority in the city.";
				String link7 = "https://en.wikipedia.org/wiki/Bulgarian_St._Stephen_Church";
				String text8 = "Hagia Triada (\"Holy Trinity\"; Greek: Ιερός Ναός Αγίας Τριάδος, romanized: Ierós Naós Agías Triádos; Turkish: Aya Triada Rum Ortodoks Kilisesi) is a Greek Orthodox church in Istanbul, Turkey. The building was erected in 1880 and is considered the largest Greek Orthodox shrine in Istanbul today. It is still in use by the Greek community of Istanbul. It has about 150 parishioners. The church is located in the district of Beyoğlu, in the neighborhood of Katip Çelebi, on Meşelik sokak, near Taksim Square.";
				String link8 = "https://en.wikipedia.org/wiki/Hagia_Triada_Church,_Istanbul";
				String text9 = "Saint Mary Draperis (Italian: Santa Maria Draperis, Turkish: Meryem Ana Draperis Latin Katolik Kilisesi) is a Catholic church in Istanbul, important for historical reasons. Established in 1584, the church is one of the most ancient Catholic parishes of Istanbul.\r\n"
						+ "\r\n"
						+ "The edifice lies in Istanbul, in the district of Beyoğlu, at 215, Istiklal Caddesi, (the ancient Grande Rue de Pera), at the bottom of a steep staircase, which is protected by an artistic fence.";
				String link9 = "https://en.wikipedia.org/wiki/Church_of_St._Mary_Draperis,_Istanbul";
				String text10 = "Gülhane Park (Turkish: Gülhane Parkı, \"Rosehouse Park\") is a historical urban park in the Eminönü district of Istanbul, Turkey; it is adjacent to and on the grounds of the Topkapı Palace. The south entrance of the park sports one of the larger gates of the palace. It is the oldest and one of the most expansive public parks in Istanbul.";
				String link10 = "https://en.wikipedia.org/wiki/G%C3%BClhane_Park";
				String text11 = "Yıldız Park (Turkish: Yıldız Parkı) is a historical, urban park in Beşiktaş district of Istanbul, Turkey. It is one of the largest public parks in Istanbul. The park is located in Yıldız quarter between the palaces of Yıldız and Çırağan.";
				String link11 = "https://en.wikipedia.org/wiki/Y%C4%B1ld%C4%B1z_Park";
				String text12 = "Atatürk Arboretum is an arboretum in Bahçeköy, Sarıyer, Istanbul Province, Turkey. The arboretum covers an area of 296 ha (730 acres) southeast of Belgrad Forest. It contains the 1818-built Kirazlı Dam (Turkish: Kirazlıbent) and a 1916-established plant nursery.";
				String link12 = "https://en.wikipedia.org/wiki/Atat%C3%BCrk_Arboretum";
				
				Destination destSaved1 = new Destination("Camlıca Mosque", text1, link1, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2a/Camlica-2018-06-14.jpg/1024px-Camlica-2018-06-14.jpg", cat3);
				Destination destSaved2 = new Destination("Topkapi Palace", text2, link2, "https://upload.wikimedia.org/wikipedia/commons/thumb/8/83/Istanbul_asv2020-02_img14_Topkap%C4%B1_Palace.jpg/332px-Istanbul_asv2020-02_img14_Topkap%C4%B1_Palace.jpg", cat4);
				Destination destSaved3 = new Destination("Beylerbeyi Palace", text3, link3, "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Istanbul_Beylerbeyi_Palace_IMG_7663_1805.jpg/405px-Istanbul_Beylerbeyi_Palace_IMG_7663_1805.jpg", cat4);
				Destination destSaved4 = new Destination("Cıragan Palace", text4, link4, "https://upload.wikimedia.org/wikipedia/commons/thumb/5/54/Istanbul_asv2020-02_img59_%C3%87%C4%B1ra%C4%9Fan_Palace.jpg/1024px-Istanbul_asv2020-02_img59_%C3%87%C4%B1ra%C4%9Fan_Palace.jpg", cat4);
				Destination destSaved5 = new Destination("Suleymaniye Mosque", text5, link5, "https://upload.wikimedia.org/wikipedia/commons/thumb/3/38/S%C3%BCleymaniyeMosqueIstanbul_%28cropped%29.jpg/390px-S%C3%BCleymaniyeMosqueIstanbul_%28cropped%29.jpg", cat3);
				Destination destSaved6 = new Destination("Hagia Sophia", text6, link6, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/22/Hagia_Sophia_Mars_2013.jpg/1024px-Hagia_Sophia_Mars_2013.jpg", cat3);
				
				Destination destSaved7 = new Destination("St. Stephen Church", text7, link7, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ab/Istanbul_asv2021-11_img13_StStephen_Church.jpg/1024px-Istanbul_asv2021-11_img13_StStephen_Church.jpg", cat1);
				Destination destSaved8 = new Destination("Hagia Triada Church", text8, link8, "https://upload.wikimedia.org/wikipedia/commons/thumb/c/ca/Agia_Triada_Greek_Orthodox_Church%2C_%C4%B0stanbul.jpg/1024px-Agia_Triada_Greek_Orthodox_Church%2C_%C4%B0stanbul.jpg", cat1);
				Destination destSaved9 = new Destination("Church of St. Mary Draperis", text9, link9, "https://upload.wikimedia.org/wikipedia/commons/thumb/6/66/Santa_Maria_Draperis_01.JPG/1024px-Santa_Maria_Draperis_01.JPG", cat1);
				Destination destSaved10 = new Destination("Gulhane Park", text10, link10, "https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/G%C3%BClhane_Park%2C_Istanbul.jpg/1024px-G%C3%BClhane_Park%2C_Istanbul.jpg", cat2);
				Destination destSaved11 = new Destination("Yildiz Park", text11, link11, "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a7/Yildiz_Park_03.jpg/1024px-Yildiz_Park_03.jpg", cat2);
				Destination destSaved12 = new Destination("Ataturk Arboretum", text12, link12, "https://upload.wikimedia.org/wikipedia/commons/thumb/2/2b/Atat%C3%BCrkArboretum_%2817%29.jpg/1024px-Atat%C3%BCrkArboretum_%2817%29.jpg", cat2);
				
				destRepo.save(destSaved1);
				destRepo.save(destSaved2);
				destRepo.save(destSaved3);
				destRepo.save(destSaved4);
				destRepo.save(destSaved5);
				destRepo.save(destSaved6);
				
				destRepo.save(destSaved7);
				destRepo.save(destSaved8);
				destRepo.save(destSaved9);
				destRepo.save(destSaved10);
				destRepo.save(destSaved11);
				destRepo.save(destSaved12);		
				
			}
		}
	}
	
	public Itinerary addDestination (String title, Destination dest) {
		// User user = userRepository.findById(userId)
		// .orElseThrow(() -> new RuntimeException("User not found"));
		Itinerary itinerary = itineraryRepo.findByName(title);
		
		if (itinerary==null)
			throw new ItineraryException("No such itinerary!");
		
		itinerary.setList(dest);
		Itinerary itinerarySaved = itineraryRepo.save(itinerary);
		return itinerarySaved;
	}
	
	public Itinerary dropDestination (String title) {
		//if (user==null)
			//throw new UserException("User not found");
		Itinerary itinerary = itineraryRepo.findByName(title);
		
		if (itinerary==null)
			throw new ItineraryException("No such itinerary!");
		if(!itinerary.getList().isEmpty())
			// removes the last element
			itinerary.getList().remove(itinerary.getList().size() - 1);
		Itinerary itinerarySaved = itineraryRepo.save(itinerary);

		return itinerarySaved;	
	}
	
}
