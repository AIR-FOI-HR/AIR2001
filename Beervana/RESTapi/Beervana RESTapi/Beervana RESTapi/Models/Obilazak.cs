using System;
using System.Collections.Generic;

namespace Beervana_RESTapi.Models
{
    public partial class Obilazak
    {
        public int IdObilazak { get; set; }
        public int IdLokacija { get; set; }
        public string NazivObilaska { get; set; }
        public DateTime DatumIVrijemeObilaska { get; set; }
        public int? BrojLjudi { get; set; }
        public string OpisRuteObilaska { get; set; }
        public double? TrajanjeObilaska { get; set; }
        public DateTime DatumKreiranja { get; set; }
        public int IdKorisnik { get; set; }

        public virtual Korisnik IdKorisnikNavigation { get; set; }
        public virtual Lokacija IdLokacijaNavigation { get; set; }
    }
}
