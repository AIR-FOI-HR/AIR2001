using System;
using System.Collections.Generic;

namespace Beervana_RESTapi.Models
{
    public partial class Clanstvo
    {
        public Clanstvo()
        {
            Korisnik = new HashSet<Korisnik>();
        }

        public int IdClanstvo { get; set; }
        public string VrstaClanstva { get; set; }
        public double CijenaClanstva { get; set; }
        public string VrijemeTrajanja { get; set; }

        public virtual ICollection<Korisnik> Korisnik { get; set; }
    }
}
