using System;
using System.Collections.Generic;

namespace Beervana_RESTapi.Models
{
    public partial class Uloga
    {
        public Uloga()
        {
            Korisnik = new HashSet<Korisnik>();
        }

        public int IdUloga { get; set; }
        public string NazivUloge { get; set; }

        public virtual ICollection<Korisnik> Korisnik { get; set; }
    }
}
