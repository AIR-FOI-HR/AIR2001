using System;
using System.Collections.Generic;

namespace Beervana_RESTapi.Models
{
    public partial class Kategorija
    {
        public Kategorija()
        {
            Proizvod = new HashSet<Proizvod>();
        }

        public int IdKategorija { get; set; }
        public string NazivKategorije { get; set; }

        public virtual ICollection<Proizvod> Proizvod { get; set; }
    }
}
