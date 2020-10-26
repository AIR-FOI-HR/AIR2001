using System;
using System.Collections.Generic;

namespace Beervana_RESTapi.Models
{
    public partial class Dogadaj
    {
        public Dogadaj()
        {
            DogadajNaLokaciji = new HashSet<DogadajNaLokaciji>();
        }

        public int IdDogadaj { get; set; }
        public string NazivDogadaja { get; set; }
        public string OpisDogađaja { get; set; }
        public string VizualDogađaja { get; set; }
        public DateTime DatumKreiranja { get; set; }
        public int IdKorisnik { get; set; }

        public virtual Korisnik IdKorisnikNavigation { get; set; }
        public virtual ICollection<DogadajNaLokaciji> DogadajNaLokaciji { get; set; }
    }
}
